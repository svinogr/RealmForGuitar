package com.example.realmforguitar.readers;

import android.content.Context;

import com.example.realmforguitar.model.AckordTDO;
import com.example.realmforguitar.model.GroupTDO;
import com.example.realmforguitar.model.SongTDO;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParserJson {
    private Context context;
    private final static String JSON = "json";

    public ParserJson(Context context) {
        this.context = context;
    }

    public Map<GroupTDO, List<SongTDO>> parse() {
        Map<GroupTDO, List<SongTDO>> map = new HashMap<>();

//        StringBuilder str = new StringBuilder();
//        try {
//            InputStream json = context.getAssets().open(JSON);
//            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(json));
//
//            String s;
//
//            while ((s = bufferedReader.readLine()) != null) {
//                str.append(s);
//            }
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }


        try {
         //   JSONArray jsonArray = new JSONArray(str.toString());
            JSONArray jsonArray = new JSONArray(loadJSONFromAsset(context));

            for (int i = 0; i < jsonArray.length(); i++) {

                JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                String nameGroup = jsonObject.getString("name");

                if (nameGroup.equals("")) {
                    continue;
                }

                GroupTDO group = new GroupTDO();

                String pic = jsonObject.getString("pic");
                group.setName(nameGroup);
                group.setPic(pic);

                List<SongTDO> songList = map.get(group);
                if (songList == null) {
                    map.put(group, new ArrayList<SongTDO>());
                    songList = map.get(group);
                }


                SongTDO song = new SongTDO();
                JSONObject songObj = jsonObject.getJSONObject("song");
                String nameSong = songObj.getString("name");
                String textSong = songObj.getString("text");
                String clearText = songObj.getString("clearText");

                song.setName(nameSong);
                song.setText(textSong);
                song.setClearText(clearText);


                if (songList.size() == 0) {
                    songList.add(song);

                } else {
                    boolean t = false;
                    for (SongTDO s : songList) {

                        if (s.getName().equals(song.getName())) {
                          t = true;
                        }

                    }
                    if(!t){
                        songList.add(song);
                    }
                }



                JSONArray ackordsList = songObj.getJSONArray("ackords");

                for (int j = 0; j < ackordsList.length(); j++) {
                    JSONObject ackordJson = (JSONObject) ackordsList.get(j);
                    AckordTDO ackord = new AckordTDO();
                    ackord.setName(ackordJson.getString("name"));
                    ackord.setPic(ackordJson.getString("pic"));
                    song.getAckordListTDO().add(ackord);
                }


                System.out.println(nameSong);

            }


        } catch (JSONException e) {
            e.printStackTrace();
        }
        return map;
    }

    public String loadJSONFromAsset(Context context) {
        String json = null;
        try {
            InputStream is = context.getAssets().open("json");

            int size = is.available();

            byte[] buffer = new byte[size];

            is.read(buffer);

            is.close();

            json = new String(buffer, "UTF-8");


        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;

    }
}
