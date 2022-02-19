package com.mypro.tools;

import cn.hutool.core.io.resource.ResourceUtil;
import org.tritonus.sampled.convert.jorbis.JorbisFormatConversionProvider;
import org.tritonus.sampled.file.jorbis.JorbisAudioFileReader;

import javax.sound.sampled.*;
import javax.sound.sampled.spi.AudioFileReader;
import javax.sound.sampled.spi.FormatConversionProvider;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.concurrent.ExecutorService;

public class MediaTool {

    private static final ExecutorService pool = ThreadTool.pool;
    private static final AudioFileReader reader = new JorbisAudioFileReader();
    private static final FormatConversionProvider provider = new JorbisFormatConversionProvider();

    /**
     * 读取文件, 获取 AudioInputStream
     *
     * @param soundFile
     * @return
     * @throws Exception
     */
    private static AudioInputStream getAudioInputStream(String soundFile) throws Exception {
        return reader.getAudioInputStream(
                ResourceUtil.getStream("bgm" + File.separator + soundFile)
        );
    }

    /**
     * 回调函数接口, 将 可能存在的 更新 AudioInputStream 的操作通过 getNewAIS 返回
     */
    @FunctionalInterface
    private interface CallBack {
        void getNewAIS(AudioInputStream audioInputStream);
    }

    /**
     * 获取 AudioFormat 并根据需要将更新的 AudioInputStream 通过 callback 返回
     *
     * @param audioInputStream
     * @param callBack
     * @return
     */
    private static AudioFormat getAudioFormat(AudioInputStream audioInputStream, CallBack callBack) {
        AudioFormat audioFormat = audioInputStream.getFormat();
        if (audioFormat.getEncoding() != AudioFormat.Encoding.PCM_SIGNED) {
            audioFormat = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED,
                    audioFormat.getSampleRate(), 16, audioFormat.getChannels(),
                    audioFormat.getChannels() * 2, audioFormat.getSampleRate(), false);
            callBack.getNewAIS(provider.getAudioInputStream(audioFormat, audioInputStream));
        }
        return audioFormat;
    }

    /**
     * 获取并打开播放设备
     *
     * @param audioFormat
     * @return
     * @throws Exception
     */
    private static SourceDataLine getAndOpenSDL(AudioFormat audioFormat) throws Exception {
        DataLine.Info dataLineInfo = new DataLine.Info(SourceDataLine.class, audioFormat);
        SourceDataLine sourceDataLine = (SourceDataLine) AudioSystem.getLine(dataLineInfo);
        sourceDataLine.open(audioFormat);
        sourceDataLine.start();
        return sourceDataLine;
    }

    /**
     * 初始化短暂音频
     *
     * @param soundFile
     * @return
     */
    public static Object[] init(String soundFile) {
        Object[] res = new Object[2];
        try {
            final AudioInputStream[] audioInputStream = {getAudioInputStream(soundFile)};
            AudioFormat audioFormat = getAudioFormat(audioInputStream[0], ais -> audioInputStream[0] = ais);
            SourceDataLine sourceDataLine = getAndOpenSDL(audioFormat);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            int len;
            do {
                byte[] buffer = new byte[1024 * 1024];
                len = audioInputStream[0].read(buffer);
                bos.writeBytes(buffer);
            } while (len != -1);
            bos.close();
            audioInputStream[0].close();
            res[0] = sourceDataLine;
            res[1] = bos.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    /**
     * 播放长音乐
     *
     * @param fileName 文件名
     * @param isLoop   是否循环
     */
    public static void playInNewThread(String fileName, boolean isLoop) {
        pool.execute(() -> {
            do {
                try {
                    final AudioInputStream[] audioInputStream = {getAudioInputStream(fileName)};
                    SourceDataLine sourceDataLine = getAndOpenSDL(
                            getAudioFormat(
                                    audioInputStream[0], ais -> audioInputStream[0] = ais)
                    );
                    int len;
                    while (true) {
                        byte[] buffer = new byte[1024 * 1024];
                        len = audioInputStream[0].read(buffer);
                        if (len == -1) break;
                        sourceDataLine.write(buffer, 0, len);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println(fileName);
                }
            } while (isLoop);
        });
    }

    /**
     * 播放短暂音频
     *
     * @param line
     * @param soundByte
     */
    public static void play(SourceDataLine line, byte[] soundByte) {
        pool.execute(() -> line.write(soundByte, 0, soundByte.length));
    }
}
