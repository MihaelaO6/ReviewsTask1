import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Collections;

class Comparator1 implements Comparator<JSONObject> {
    @Override
    public int compare(JSONObject obj1, JSONObject obj2){
        int first = 0;
        try {
            first = (Integer) obj1.get("rating");
        }catch (JSONException e){
            e.printStackTrace();
        }
        int sec = 0;
        try {
            sec = (Integer) obj2.get("rating");
        }catch (JSONException e){
            e.printStackTrace();
        }
        assert 0 != first;
        assert sec !=0;
        return first - sec;
    }
}
class Comparator2 implements Comparator<JSONObject> {
    @Override
    public int compare(JSONObject obj1, JSONObject obj2){
        String first = " ";
        try {
            first = (String) obj1.get("reviewCreatedOnDate");
        }catch (JSONException e){
            e.printStackTrace();
        }
        String sec = " ";
        try {
            sec = (String) obj2.get("reviewCreatedOnDate");
        }catch (JSONException e){
            e.printStackTrace();
        }
        assert null != first;
        assert sec !=null;
        return first.compareTo(sec);
    }
}
public class Reviews {
    public static void main(String[] args) throws IOException, JSONException {
        String data = new String(Files.readAllBytes(Paths.get("./reviews.json")));
        JSONArray jsonArray = new JSONArray(data);
        ArrayList<JSONObject> list1 = new ArrayList<>();
        for (int i = 0; i<jsonArray.length();i++) {
            String str  = jsonArray.get(i).toString();
            JSONObject obj = new JSONObject(str);
            list1.add(obj);
        }
        list1.sort(new Comparator1());
        jsonArray = new JSONArray(list1);
        System.out.println("Sorted by: rating - lowest first");
        for (int i = 0; i<jsonArray.length();i++){
            JSONObject object1 = new JSONObject(jsonArray.get(i).toString());
            String name = object1.getString("reviewerName");
            int id = object1.getInt("id");
            int rating = object1.getInt("rating");
            System.out.println(id);
            System.out.println(rating);
        }
        System.out.println("OR sorted by: rating - highest first");
        Collections.reverse(list1);
        jsonArray = new JSONArray(list1);
        for (int i = 0; i<jsonArray.length();i++){
            JSONObject object1 = new JSONObject(jsonArray.get(i).toString());
            String name = object1.getString("reviewerName");
            int id = object1.getInt("id");
            int rating = object1.getInt("rating");
            System.out.println(id);
            System.out.println(rating);
        }
        System.out.println("Sorted by date: Oldest First");
        for (int i = 0; i<jsonArray.length();i++) {
            String str  = jsonArray.get(i).toString();
            JSONObject obj = new JSONObject(str);
            list1.add(obj);
        }
        list1.sort(new Comparator2());
        jsonArray = new JSONArray(list1);
        for (int i = 0; i<jsonArray.length();i++){
            JSONObject object1 = new JSONObject(jsonArray.get(i).toString());
            String date = object1.getString("reviewCreatedOnDate");
            int id = object1.getInt("id");
            int rating = object1.getInt("rating");
            System.out.println(id);
            System.out.println(date);
        }
        Collections.reverse(list1);
        System.out.println("Sorted by date: Newest First");
        jsonArray = new JSONArray(list1);
        for (int i = 0; i<jsonArray.length();i++){
            JSONObject object1 = new JSONObject(jsonArray.get(i).toString());
            String date = object1.getString("reviewCreatedOnDate");
            int id = object1.getInt("id");
            int rating = object1.getInt("rating");
            System.out.println(id);
            System.out.println(date);
        }
        System.out.println("Prioritize by text:Yes");
        jsonArray = new JSONArray(list1);
        for (int i = 0; i < jsonArray.length(); i++) {
            String str = jsonArray.get(i).toString();
            JSONObject obj1 = new JSONObject(str);
            String reviewText = obj1.getString("reviewText");
            int id = obj1.getInt("id");
            if (reviewText != "") {
                System.out.println(id);
                System.out.println(reviewText);
            }
        }
    }
}