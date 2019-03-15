package com.tanc.cartoonloader;

import org.apache.commons.cli.*;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;


public class App {

    public static void main(String[] args) throws InterruptedException, MalformedURLException {
        Options options = new Options();
        options.addOption("c", "comic", true, "输入漫画的拼音");
        options.addOption("H", "hash", true, "输入漫画卷数");
        options.addOption("o", "output-dir", true, "输入想要存储的位置");
        options.addOption("h", "help", false, "输出help");

        DefaultParser cmdParser = new DefaultParser();
        try {

            CommandLine cmdLine = cmdParser.parse(options, args);

            if(cmdLine.hasOption('h')){
                printHelp(options);
                System.exit(0);
            }

            if(cmdLine.getOptionValue('c') == null){
                System.out.println("没有输入漫画拼音");
                printHelp(options);
                System.exit(1);
            }
            if(cmdLine.getOptionValue('H') == null){
                System.out.println("没有输入卷数");
                printHelp(options);
                System.exit(1);
            }
            if(cmdLine.getOptionValue('o') == null){
                System.out.println("没有输入要保存的路径");
                printHelp(options);
                System.exit(1);
            }

            String comicId = cmdLine.getOptionValue('c');
            String hash = cmdLine.getOptionValue('H');
            hash = StringUtils.leftPad(hash, 2, "0");

            File outputDir = new File(cmdLine.getOptionValue('o'));

            outputDir.mkdirs();

            for (int i = 1; i < 150; i++) {
                String num;
                if(i<10){
                    num = StringUtils.leftPad(String.valueOf(i),3, "0");
                }else{
                    num = StringUtils.leftPad(String.valueOf(i),3, "0");
                }
                String urlStr = "https://www.cartoonmad.com/comic/comicpic.asp?file=/" + comicId + "/" + hash + "/" + num + ".jpg";
                URL url = new URL(urlStr);
                File destFile = new File(outputDir, num+".jpg");


                FileUtils.copyURLToFile(url, destFile);
                System.out.println(urlStr + " --> " + destFile.getAbsolutePath());


                Thread.sleep(1000);

            }

            System.out.println("Download Complete");


        } catch (ParseException e) {
            printHelp(options);
            System.exit(1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void printHelp(Options options) {
        HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp("cldr <options>", options);
    }
}
