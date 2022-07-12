package resources;

import java.util.ArrayList;
import java.util.List;

import pojo.AddPlace;
import pojo.Location;

public class TestDataBuild
{
	public AddPlace addPlacePayLoad()
	{
		AddPlace p =new AddPlace();
        p.setAccuracy(50);
        p.setAddress("carrer 26");
        p.setLanguage("French-IN");
        p.setPhone_number("+573212141352");
        p.setWebsite("http://google.com");
        p.setName("Manizales");
        List<String>myList =new ArrayList<String>();
        myList.add("shoe park");
        myList.add("shop");
        p.setTypes(myList);
        Location l =new Location();
        l.setLat( -38.383494);
        l.setLng(33.427362);
        p.setLocation(l);
        return p;
	}

}
