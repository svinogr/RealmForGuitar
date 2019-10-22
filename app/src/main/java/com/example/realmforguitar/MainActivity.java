package com.example.realmforguitar;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.realmforguitar.dao.AckordDao;
import com.example.realmforguitar.dao.GroupDao;
import com.example.realmforguitar.dao.SongDao;
import com.example.realmforguitar.model.Ackord;
import com.example.realmforguitar.model.AckordTDO;
import com.example.realmforguitar.model.Group;
import com.example.realmforguitar.model.GroupTDO;
import com.example.realmforguitar.model.Song;
import com.example.realmforguitar.model.SongTDO;
import com.example.realmforguitar.readers.ParserJson;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import io.realm.Realm;

public class MainActivity extends AppCompatActivity {
    Bitmap f = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ParserJson parserJson = new ParserJson(getApplicationContext());
        Map<GroupTDO, List<SongTDO>> parse = parserJson.parse();
        System.out.println(parse.size());
        //  System.out.println(parse);
        TreeMap<GroupTDO, List<SongTDO>> sorted = new TreeMap<>(parse);

        Realm.init(getApplicationContext());
        for (Map.Entry<GroupTDO, List<SongTDO>> entry : sorted.entrySet()) {

            GroupTDO key = entry.getKey();
            byte[] picData = null;
//            try {
//                InputStream open = getApplicationContext().getAssets().open(key.getPic().substring(1, key.getPic().length()));
//                picData = readBytes(open);
//
//            } catch (IOException e) {
//                e.printStackTrace();
//            }



            Group group = new Group();
            group.setName(key.getName().toLowerCase());

            picData =  getLetterTile(group.getName());

            group.setImgData(picData);


            List<SongTDO> value = entry.getValue();
            Collections.sort(value);

            SongDao songDao = new SongDao();
            for (SongTDO songTDO : value) {
                List<AckordTDO> ackordListTDO = songTDO.getAckordListTDO();

                AckordDao ackordDao = new AckordDao();
                Song song = new Song();

                for (AckordTDO ackordTDO : ackordListTDO) {
                    Ackord ackord = new Ackord();
                    String[] split = ackordTDO.getName().split("\\/");
                    String[] name = split[split.length - 1].split("\\.");
                    String name1 = name[0].toLowerCase().replace("w", "#").replace("p", "+").replace("sus", "сус").replace("s", "/").replace("сус", "sus").replace("z", "-");

                    ackord.setName(name1);

                    try {
                        String[] splitAckordPic = ackordTDO.getPic().split("\\.");
                        String newPic = splitAckordPic[0] + " копия.gif";

                        ackord.setImageData(readBytes(getApplicationContext().getAssets().open(newPic)));

                    } catch (IOException e) {
                        System.out.println(ackordTDO.getName() + "---" + ackordTDO.getPic());
                        String[] split2 = ackordTDO.getPic().split("=");
                        String name2 = split2[1].replace('/', 's');
                        try {
                            ackord.setImageData(readBytes(getApplicationContext().getAssets().open(getString(R.string.pathChords)+name2 +" копия.gif")));
                        } catch (IOException e1) {
                            System.out.println(ackordTDO.getName() + "-22-" + ackordTDO.getPic());

                            e1.printStackTrace();
                        }

                        e.printStackTrace();
                    }
                    ackord = ackordDao.createItem(ackord);
                    song.getAckords().add(ackord);
                }


                song.setText(songTDO.getText());
                song.setName(songTDO.getName().toLowerCase());
                song.setClearText(songTDO.getClearText());
                song = songDao.createItem(song);

                group.getListSongs().add(song);
            }

            GroupDao groupDao = new GroupDao();
            Group g = groupDao.createItem(group);

           // for(Song song: group.getListSongs()) {
            //    song.setParentId(g.getId());
          //      //songDao.update(song);
          //  }


        }

     /*   ReaderHtml readerHtml = new ReaderHtml(getApplicationContext());
        List<Document> documents = null;
        try {
            documents = readerHtml.getDocuments();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (documents != null) {
            Parser parser = new Parser(documents);
            Map<String, List<SongTDO>> mapGroup = parser.getMapGroup();
            System.out.println("size map " + mapGroup.size());
        }*/


        /*Realm.init(getApplicationContext());
        Realm realm = Realm.getDefaultInstance();
        Group group = new Group();
       // group.setId(1);
       // realm.beginTransaction();
        Number id = realm.where(Group.class).max("id");
        //realm.commitTransaction();

        if(id == null){
            id = 1;
        } else {id = id.intValue() + 1;}

        group.setId(id.intValue());

        realm.beginTransaction();
        group.setName("ghjgjgj");

        realm.insertOrUpdate(group);

        realm.commitTransaction();*/
        System.out.println("начало");

        ImageView i = findViewById(R.id.image);

        i.setImageBitmap(f);


    }

    public byte[] readBytes(InputStream inputStream) throws IOException {
        // this dynamically extends to take the bytes you read
        ByteArrayOutputStream byteBuffer = new ByteArrayOutputStream();

        // this is storage overwritten on each iteration with bytes
        int bufferSize = 1024;
        byte[] buffer = new byte[bufferSize];

        // we need to know how may bytes were read to write them to the byteBuffer
        int len = 0;
        while ((len = inputStream.read(buffer)) != -1) {
            byteBuffer.write(buffer, 0, len);
        }

        // and then we can return your byte array.
        return byteBuffer.toByteArray();
    }

    private byte[] getLetterTile(String name) {
        int COVER_IMAGE_SIZE = 100; //in pixels
        LetterBitmap letterBitmap = new LetterBitmap(getApplicationContext());


        Bitmap letterTile = letterBitmap.getLetterTile(name, "red" , 400, 300);

        //f = letterTile;
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        letterTile.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();

        return byteArray;
    }



}
