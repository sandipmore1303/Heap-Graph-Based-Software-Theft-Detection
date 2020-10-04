import java.util.HashMap;
import java.util.Set;

public class MyHashMapRead {
    HashMap<String, String> hm;
    MyHashMapRead(){
                hm = new HashMap<String, String>();
		//add key-value pair to hashmap
                //jQuery
		hm.put("stackoverflow.com", "jQuery");
                hm.put("github.com", "jQuery");
                hm.put("linkedin.com", "jQuery");
                hm.put("reddit.com", "jQuery");
                hm.put("twitter.com", "jQuery");
                
                hm.put("wordpress.org", "jQuery");
                hm.put("themeforest.net", "jQuery");
                hm.put("espncricinfo.com", "jQuery");
                hm.put("odesk.com", "jQuery");
                hm.put("yandex.ru", "jQuery");
                
               //Modernizr
		hm.put("www.usmagazine.com", "Modernizr");
                hm.put("www.about.com", "Modernizr");
                hm.put("www.boursorama.com", "Modernizr");
                hm.put("magazine.good.is", "Modernizr");
                hm.put("php.net", "Modernizr");
                
                hm.put("www.shopgate.guru", "Modernizr");
                hm.put("allegro.pl", "Modernizr");
                hm.put("Www.repubblica.it", "Modernizr");
                //hm.put("www.usmagazine.com", "Modernizr");
                //hm.put("www.usmagazine.com", "Modernizr");
  

//MooTools
                hm.put("www.elance.com", "MooTools");
                hm.put("www.fl.ru", "MooTools");
                hm.put("vimeo.com", "MooTools");
                hm.put("jsfiddle.net", "MooTools");
                hm.put("leprosorium.ru", "MooTools");
                
                hm.put("www.livelib.ru", "MooTools");
                hm.put("play.spotify.com", "MooTools");
                //hm.put("www.elance.com", "MooTools");
                //hm.put("www.elance.com", "MooTools");
                //hm.put("www.elance.com", "MooTools");
               

//Prototype
                




                hm.put("magentocommerce.com", "Prototype");
                hm.put("www.okcupid.com", "Prototype");
                hm.put("steamcommunity.com", "Prototype");
                hm.put("ikea.com", "Prototype");
                //hm.put("magentocommerce.com", "Prototype");
                
                //hm.put("magentocommerce.com", "Prototype");
                //hm.put("magentocommerce.com", "Prototype");
                //hm.put("magentocommerce.com", "Prototype");
                //hm.put("magentocommerce.com", "Prototype");
                //hm.put("magentocommerce.com", "Prototype");
                
                








 
		//System.out.println(hm);
		Set<String> keys = hm.keySet();
		for(String key: keys){
			//System.out.println("Value of "+key+" is: "+hm.get(key));
		}  
    }
	public static void main(String a[]){
		MyHashMapRead mh=new MyHashMapRead();
	}
}