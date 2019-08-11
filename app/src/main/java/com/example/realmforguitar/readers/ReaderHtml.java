package com.example.realmforguitar.readers;

import android.content.Context;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReferenceArray;

public class ReaderHtml {
    private Context context;

    public ReaderHtml(Context context) {
        this.context = context;
    }

    private String mask = "song";

    private List<String> readHtmlToArrayFiles() {
        List<String> fileList = new ArrayList<>();

        String[] list = null;
        try {
            list = context.getAssets().list("");

        } catch (IOException e) {
            e.printStackTrace();
        }


        for (int i = 0; i < list.length; i++) {
            if (list[i].startsWith(mask)) {
                //   System.out.println(files[i]);
                fileList.add(list[i]);
            }
        }

        System.out.println("--" + list.length);
        System.out.println("==" + list[0]);

        System.out.println(fileList.size());

        return fileList;
    }


    public List<Document> getDocuments() throws IOException {
        List<String> files = readHtmlToArrayFiles();
        List<Document> listDocuments = new ArrayList<Document>();

       /* for (String nameFile: files) {

            String strForJsoup = readFileToString(context.getAssets().open(nameFile));

            Document document = Jsoup.parse(strForJsoup);
            listDocuments.add(document);

        }*/
        String strForJsoup = null;
        for (int i = 0; i < files.size(); i++) {
            System.out.println(i);
            strForJsoup = readFileToString(context.getAssets().open(files.get(i)));

            Document document = Jsoup.parse(strForJsoup);
            listDocuments.add(document);
            strForJsoup = null;
        }
        //   }
        return listDocuments;
    }

    private String readFileToString(InputStream file) {
        StringBuilder fileStr = new StringBuilder();


        //  FileInputStream fstream = null;
        BufferedReader br = null;
        try {
            //   fstream = new FileInputStream(file);
            br = new BufferedReader(new InputStreamReader(file));

            String strLine;
            int ch;
            while ((ch = file.read()) != -1) {

                fileStr.append((char) ch);

            }
            br.close();
            file.close();
        } catch (FileNotFoundException | NullPointerException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {

            try {
                //   fstream.close();
                br.close();
                file.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        return fileStr.toString();
    }
}