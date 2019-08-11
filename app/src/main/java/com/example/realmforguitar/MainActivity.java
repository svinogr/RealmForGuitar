package com.example.realmforguitar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import io.realm.Realm;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ParserJson parserJson = new ParserJson(getApplicationContext());
        Map<GroupTDO, List<SongTDO>> parse = parserJson.parse();
        System.out.println(parse.size());
      //  System.out.println(parse);
        Realm.init(getApplicationContext());
      for(  Map.Entry<GroupTDO, List<SongTDO>> entry: parse.entrySet()){

          GroupTDO key = entry.getKey();
          byte[] picData = null;
          try {
              InputStream open = getApplicationContext().getAssets().open(key.getPic().substring(1, key.getPic().length()));
           picData = readBytes(open);

          } catch (IOException e) {
              e.printStackTrace();
          }

          Group group = new Group();
          group.setName(key.getName());
          group.setImgData(picData);



          List<SongTDO> value = entry.getValue();
          SongDao songDao = new SongDao();
         for(SongTDO songTDO: value) {
             List<AckordTDO> ackordListTDO = songTDO.getAckordListTDO();

             AckordDao ackordDao = new AckordDao();
             Song song = new Song();

             for (AckordTDO ackordTDO: ackordListTDO) {
                 Ackord ackord = new Ackord();
                 String[] split = ackordTDO.getName().split("\\/");
                 String[] name = split[split.length - 1].split("\\.");
                String name1 =  name[0].toLowerCase().replace("w","#").replace("p","+").replace("sus", "сус").replace("s", "/").replace("сус", "sus").replace("z", "-");

                 ackord.setName(name1);

                 try {
                     ackord.setImageData(readBytes(getApplicationContext().getAssets().open(ackordTDO.getPic())));
                 } catch (IOException e) {
                     System.out.println(ackordTDO.getName() + "---" + ackordTDO.getPic());
                     e.printStackTrace();
                 }
                ackord = ackordDao.createItem(ackord);
                 song.getAckords().add(ackord);
             }


             song.setText(songTDO.getText());
             song.setName(songTDO.getName());
            song =  songDao.createItem(song);
             group.getListSings().add(song);
         }

          GroupDao groupDao = new GroupDao();
          groupDao.createItem(group);



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
}
