package resourses;

import java.util.ArrayList;
import java.util.List;

import pojo.AddPlace;
import pojo.Location;

public class TestDataBuild {

	 public AddPlace addPlacePayLoad(String name ,String language ,String address)
	 {
		 AddPlace addplace=new AddPlace();
			
			addplace.setAccuracy(50);
			addplace.setAddress(address);
			addplace.setLanguage(language);
			addplace.setName(name);
			addplace.setPhone_number("(+91) 983 893 3937");
			addplace.setWebsite("http://google.com");
			
			List<String> myTypes=new ArrayList<String>();
			myTypes.add("shoe park");
			myTypes.add("Narayanpur");
			addplace.setTypes(myTypes);
			
			Location loc=new Location();
			loc.setLng(-38.383494);
			loc.setLat(33.427362);
			addplace.setLocation(loc);
			
			return addplace ;
	 }
	 
	 public String deletePlacePayload(String place_id)
	 {
		 String body= "{\"place_id\" : \""+place_id+"\"}";
		 return body;
	 }
}
