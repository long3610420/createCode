package com.github.common;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/**
 * д�ļ�
 * @author Administrator
 *
 */
public class FileUtil {
	
	public static void writeFile(String fileName , String content) throws Exception {
		
		File file = new File(fileName);
		if(!file.exists()){
		    file.createNewFile();
		}
		
		try {
			FileWriter fw = new FileWriter(fileName);
			fw.write(content);
			fw.flush(); 
			fw.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void fileCopy(String sFile, String oFile) {
        File file = new File(sFile);

        if (!file.exists()) {
            System.out.println(sFile + " not have");
            return;
        }
        File fileb = new File(oFile);

        if (file.isFile()) {
            FileInputStream fis = null;
            FileOutputStream fos = null;
            try {
                fis = new FileInputStream(file);
                fos = new FileOutputStream(fileb);

                byte[] bb = new byte[(int) file.length()];
                fis.read(bb);
                fos.write(bb);

            }
            catch(IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    fis.close();
                    fos.close();
                }
                catch(IOException e) {
                    e.printStackTrace();
                }
            }
        }
        else if (file.isDirectory()) {
            if (!fileb.exists()) {
                fileb.mkdir();
            }
            String[] fileList;
            fileList = file.list();
            for (int i = 0; i < fileList.length; i++) {
                fileCopy(sFile + "/" + fileList[i], oFile + "/" + fileList[i]);
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
            // for (int j = 1; (temp = br.readLine()) != null
            // && !temp.equals(oldStr); j++) {
            // buf = buf.append(temp);
            // buf = buf.append(System.getProperty("line.separator"));
            // }
            //
            // // �����ݲ���
            // buf = buf.append(replaceStr);

            // ������к��������
            while ((temp = br.readLine()) != null) {
                if (temp.equals(oldStr)) {
                    buf = buf.append(replaceStr);
                    buf = buf.append(System.getProperty("line.separator"));

                }
                else {
                    buf = buf.append(temp);
                    buf = buf.append(System.getProperty("line.separator"));
                }
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
     * ���ļ������ݵ�ת���ַ�����.
     * 
     * @param path
     *            ·��
     */
    public static String[] readTxtToStr(String path) {
        String temp = "";
        int count = 0;
        int i = 0;
        String[] str = null;
        try {
            File file = new File(path);
            FileInputStream fis = new FileInputStream(file);
            FileInputStream fis1 = new FileInputStream(file);
            InputStreamReader isr = new InputStreamReader(fis);
            InputStreamReader isr1 = new InputStreamReader(fis1);
            BufferedReader br = new BufferedReader(isr);
            BufferedReader br1 = new BufferedReader(isr1);
            while (br.ready()) {
                br.readLine();
                count++;
            }
            str = new String[count];
            while ((temp = br1.readLine()) != null) {
                str[i] = temp.trim();
                i++;
            }

            br.close();
            br1.close();
            fis.close();
            fis1.close();
            isr.close();
            isr1.close();
        }
        catch(IOException e) {
            e.printStackTrace();
        }
        return str;
    }

}
