/*
package com.example.realmforguitar.readers;

import com.example.realmforguitar.model.Song;
import com.example.realmforguitar.model.SongTDO;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Parser {
    List<Document> listDocuments;

    public Parser(List<Document> listDocuments) {
        this.listDocuments = listDocuments;
    }

    public Map<String, List<SongTDO>> getMapGroup() {
        Map<String, List<SongTDO>> map = new HashMap<>();
        int count = 0;
        for (Document document : listDocuments) {
          //  System.out.println(document.body());
            String textSong = document.body().getElementsByTag("pre").toString();
            System.out.println(textSong);
            Elements elementsByAttributeValueContaining = document.body().getElementsContainingOwnText( "Версия для печати");
            Elements elementsByAttributeValueContaininAhref = document.body().getElementsContainingOwnText( "Оригинальные аккорды");

            System.out.println("p"+elementsByAttributeValueContaining);
            System.out.println("href"+elementsByAttributeValueContaininAhref);



            if(!elementsByAttributeValueContaining.text().equals("")){

                continue;
            }

            if(!elementsByAttributeValueContaininAhref.text().equals("")){
                continue;
            }

            String nameGroup = document.body().getElementsByAttributeValue("itemprop", "byArtist").text();
            System.out.println("-------------------" + nameGroup);
            List<SongTDO> songList = map.get(nameGroup);

            if (songList == null) {
                map.put(nameGroup, new ArrayList<SongTDO>());
                songList = map.get(nameGroup);
                System.out.println(count++);
            }

            String nameSong = document.body().getElementsByAttributeValue("itemprop", "name").first().text();
            System.out.println(nameSong);
            String textSong2 = document.body().getElementsByTag("pre").toString();


            Song songOr = new Song();
            SongTDO song = new SongTDO(songOr);
            song.getSongOr().setText(textSong);
            song.getSongOr().setName(nameSong);

            Elements fingering = document.body().getElementsByClass("fingering");
            Elements imgs = Jsoup.parse(fingering.html()).getElementsByTag("img");


            for (Element el :
                    imgs) {
                // System.out.println(el);
                String nameAcckord = el.attr("src");
                song.getAckordListTDO().add(nameAcckord);

            }
            songList.add(song);
        }

        return map;
    }
}
*/
