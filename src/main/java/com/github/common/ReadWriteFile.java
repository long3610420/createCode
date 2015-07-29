package com.github.common;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.RandomAccessFile;

/**
 * 
 * ��������������TXT�ļ������ж���д���޸Ĳ���
 * 
 * @author <a href=">
 * @version 1.0 Creation date: 2007-12-18 - ����06:48:45
 */
public class ReadWriteFile {
    public static BufferedReader bufread;

    // ָ���ļ�·�������
    private static String path = "D:/suncity.txt";

    private static File filename = new File(path);

    private static String readStr = "";

    /**
     * �����ı��ļ�.
     * 
     * @throws IOException
     * 
     */
    public static void creatTxtFile() throws IOException {
        if (!filename.exists()) {
            filename.createNewFile();
            System.err.println(filename + "�Ѵ�����");
        }
    }

    /**
     * ��ȡ�ı��ļ�.
     * 
     */
    public static String readTxtFile() {
        String read;
        FileReader fileread;
        try {
            fileread = new FileReader(filename);
            bufread = new BufferedReader(fileread);
            try {
                while ((read = bufread.readLine()) != null) {
                    readStr = readStr + read + "/r/n";
                }
            }
            catch(IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        catch(FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        System.out.println("�ļ�������:" + "/r/n" + readStr);
        return readStr;
    }

    /**
     * д�ļ�.
     * 
     */
    public static void writeTxtFile(String newStr) throws IOException {
        // �ȶ�ȡԭ���ļ����ݣ�Ȼ�����д�����
        String filein = newStr + "/r/n" + readStr + "/r/n";
        RandomAccessFile mm = null;
        try {
            mm = new RandomAccessFile(filename, "rw");
            mm.writeBytes(filein);
        }
        catch(IOException e1) {
            // TODO �Զ���� catch ��
            e1.printStackTrace();
        } finally {
            if (mm != null) {
                try {
                    mm.close();
                }
                catch(IOException e2) {
                    // TODO �Զ���� catch ��
                    e2.printStackTrace();
                }
            }
        }
    }

    /**
     * ���ļ���ָ�����ݵĵ�һ���滻Ϊ��������.
     * 
     * @param oldStr
     *            ��������
     * @param replaceStr
     *            �滻����
     */
    public static void replaceTxtByStr(String path, String oldStr,
            String replaceStr) {
        String temp = "";
        try {
            File file = new File(path);
            FileInputStream fis = new FileInputStream(file);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            StringBuffer buf = new StringBuffer();

            // �������ǰ�������
            for (int j = 1; (temp = br.readLine()) != null
                    && !temp.equals(oldStr); j++) {
                buf = buf.append(temp);
                buf = buf.append(System.getProperty("line.separator"));
            }

            // �����ݲ���
            buf = buf.append(replaceStr);

            // ������к��������
            while ((temp = br.readLine()) != null) {
                buf = buf.append(System.getProperty("line.separator"));
                buf = buf.append(temp);
            }

            br.close();
            FileOutputStream fos = new FileOutputStream(file);
            PrintWriter pw = new PrintWriter(fos);
            pw.write(buf.toString().toCharArray());
            pw.flush();
            pw.close();
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * main��������
     * 
     * @param s
     * @throws IOException
     */
    public static void main(String[] s) throws IOException {
        ReadWriteFile.creatTxtFile();
        ReadWriteFile.readTxtFile();
        ReadWriteFile.writeTxtFile("20080808:12:13");
        // ReadWriteFile.replaceTxtByStr("ken", "zhang");
    }
}

