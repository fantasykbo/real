package openapi;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import media.dto.MediaDTO;
//https://www.youtube.com/watch?v=videoId

public class youtubeAPI {
	public ArrayList<MediaDTO> parser(String teamname) throws UnsupportedEncodingException{
		String key = "AIzaSyBxKEEJ5vrVJoh0oY2iAjazjG_b3F0sN2I";
		String query = URLEncoder.encode(teamname,"utf-8");
		String url = "https://www.googleapis.com/youtube/v3/search?"
				+ "part=snippet"
				+ "&channelId=UCtm_QoN2SIxwCE-59shX7Qg"
				+ "&maxResults=6"
				+ "&order=date&"
				+ "q="+query
				+ "&key="+key;
		
		ArrayList<MediaDTO> media = new ArrayList<MediaDTO>();
		//(id->videoId), (snippet-> publishedAt, title, description, (default->url(image))), channelTitle
		try{
			
			URL posturl = new URL(url);
			InputStreamReader isr = new InputStreamReader(posturl.openConnection().getInputStream(),"UTF-8");
			JSONObject object=(JSONObject)JSONValue.parseWithException(isr);
			//Object next = object.get("nextPageToken");
			//Object prev = object.get("prevPageToken");
			JSONArray items = (JSONArray)object.get("items");
			
			for(int i=0;i<items.size();i++){
				JSONObject item = (JSONObject)items.get(i);
				JSONObject id =(JSONObject)item.get("id");
				JSONObject snippet=(JSONObject)item.get("snippet");
				JSONObject thumbnails =(JSONObject)snippet.get("thumbnails");
				JSONObject def =(JSONObject)thumbnails.get("default");
				
				Object videoId = id.get("videoId");
				Object publishedAt =snippet.get("publishedAt");
				Object title=snippet.get("title");
				Object description=snippet.get("description");
				Object images = def.get("url");
				
				MediaDTO m = new MediaDTO(videoId, publishedAt, title, description, images);
				media.add(m);

			}
			
		
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return media;
		
	}
		

}
