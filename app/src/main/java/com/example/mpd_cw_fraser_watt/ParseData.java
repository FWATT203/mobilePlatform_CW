package com.example.mpd_cw_fraser_watt;         //FRASER WATT - S1620179


import android.util.Log;
import android.widget.TextView;
import java.io.IOException;
import java.io.StringReader;
import java.util.LinkedList;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

public class ParseData {

    private LinkedList <CurrentIncidents> alist;
    private LinkedList <Roadworks> alist_2;
    private LinkedList <PlannedRoadworks> alist_3;
    private TextView acknowledgement;



////////////////////////////////////////////////////////////////////////////////////////////////////



    public ParseData(){

    }

    public void parseAllSearch(MainActivity ma, String incidentData, String roadworkData, String plannedRoadworkData, String userSearch){
        acknowledgement = ma.findViewById(R.id.acknowledgement);
        alist = null;
        alist_2 = null;
        alist_3 = null;
        CurrentIncidents incident = new CurrentIncidents();
        Roadworks roadwork = new Roadworks();
        PlannedRoadworks plannedRw = new PlannedRoadworks();
        final String search = userSearch;



////////////////////////////////////////////////////////////////////////////////////////////////////

        try
        {
            System.out.println(incidentData);

            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(true);
            XmlPullParser xpp = factory.newPullParser();
            xpp.setInput( new StringReader( incidentData ) );
            int eventType = xpp.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT)
            {

                if(eventType == XmlPullParser.START_TAG)
                {
                    System.out.println("This is the start tag");
                    if (xpp.getName().equalsIgnoreCase("channel"))
                    {
                        alist  = new LinkedList<CurrentIncidents>();
                    }
                    else if (xpp.getName().equalsIgnoreCase("item"))
                    {
                        incident = new CurrentIncidents();
                    }
                    else if (xpp.getName().equalsIgnoreCase("title"))
                    {
                        String temp = xpp.nextText();
                        incident.setTitle(temp);
                    }
                    else if (xpp.getName().equalsIgnoreCase("point"))
                    {
                        String temp = xpp.nextText();
                        incident.setCoordinates(temp);
                        incident.splitCoords(temp);
                    }
                    else
                    if (xpp.getName().equalsIgnoreCase("pubDate"))
                    {
                        String temp = xpp.nextText();
                        incident.setPublishDate(temp);
                    }
                }
                else if(eventType == XmlPullParser.END_TAG)
                {
                    if (xpp.getName().equalsIgnoreCase("item"))
                    {
                        Log.e("MyTag","Incident is " + incident.toString());
                        alist.add(incident);
                    }
                    else if (xpp.getName().equalsIgnoreCase("channel"))
                    {
                        int size;
                        size = alist.size();
                        Log.e("MyTag","Roadworks size is " + size);
                    }
                }

                eventType = xpp.next();
            } // End of while
        }
        catch (XmlPullParserException ae1)
        {
            Log.e("MyTag","Parsing error" + ae1.toString());
        }
        catch (IOException ae1)
        {
            Log.e("MyTag","IO error during parsing");
        }



////////////////////////////////////////////////////////////////////////////////////////////////////



        try
        {
            System.out.println(roadworkData);

            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(true);
            XmlPullParser xpp = factory.newPullParser();
            xpp.setInput( new StringReader( roadworkData ) );
            int eventType = xpp.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT)
            {

                if(eventType == XmlPullParser.START_TAG)
                {
                    System.out.println("This is the start tag");
                    if (xpp.getName().equalsIgnoreCase("channel"))
                    {
                        alist_2 = new LinkedList<Roadworks>();
                    }
                    else if (xpp.getName().equalsIgnoreCase("item"))
                    {
                        roadwork = new Roadworks();
                    }
                    else if (xpp.getName().equalsIgnoreCase("title"))
                    {
                        String temp = xpp.nextText();
                        roadwork.setTitle(temp);
                    }
                    else if (xpp.getName().equalsIgnoreCase("point"))
                    {
                        String temp = xpp.nextText();
                        roadwork.setCoordinates(temp);
                        roadwork.splitCoords(temp);
                    }
                    else
                    if (xpp.getName().equalsIgnoreCase("pubDate"))
                    {
                        String temp = xpp.nextText();
                        roadwork.setPublishDate(temp);
                    }
                }
                else if(eventType == XmlPullParser.END_TAG)
                {
                    if (xpp.getName().equalsIgnoreCase("item"))
                    {
                        Log.e("MyTag","Incident is " + roadwork.toString());
                        alist_2.add(roadwork);
                    }
                    else if (xpp.getName().equalsIgnoreCase("channel"))
                    {
                        int size;
                        size = alist_2.size();
                        Log.e("MyTag","Roadworks size is " + size);
                    }
                }

                eventType = xpp.next();
            }
        }
        catch (XmlPullParserException ae1)
        {
            Log.e("MyTag","Parsing error" + ae1.toString());
        }
        catch (IOException ae1)
        {
            Log.e("MyTag","IO error during parsing");
        }



////////////////////////////////////////////////////////////////////////////////////////////////////



        try
        {
            System.out.println(plannedRoadworkData);

            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(true);
            XmlPullParser xpp = factory.newPullParser();
            xpp.setInput( new StringReader( plannedRoadworkData ) );
            int eventType = xpp.getEventType();

            while (eventType != XmlPullParser.END_DOCUMENT)
            {

                if(eventType == XmlPullParser.START_TAG)
                {
                    System.out.println("This is the start tag");
                    if (xpp.getName().equalsIgnoreCase("channel"))
                    {
                        alist_3 = new LinkedList<PlannedRoadworks>();
                    }
                    else if (xpp.getName().equalsIgnoreCase("item"))
                    {
                        plannedRw = new PlannedRoadworks();
                    }
                    else if (xpp.getName().equalsIgnoreCase("title"))
                    {
                        String temp = xpp.nextText();
                        plannedRw.setTitle(temp);
                    }
                    else if (xpp.getName().equalsIgnoreCase("point"))
                    {
                        String temp = xpp.nextText();
                        plannedRw.setCoordinates(temp);
                        plannedRw.splitCoords(temp);
                    }
                    else
                    if (xpp.getName().equalsIgnoreCase("pubDate"))
                    {
                        String temp = xpp.nextText();
                        plannedRw.setPublishDate(temp);
                    }
                }

                else if(eventType == XmlPullParser.END_TAG)
                {
                    if (xpp.getName().equalsIgnoreCase("item"))
                    {
                        Log.e("MyTag","Incident is " + plannedRw.toString());
                        alist_3.add(plannedRw);
                    }
                    else if (xpp.getName().equalsIgnoreCase("channel"))
                    {
                        int size;
                        size = alist_3.size();
                        Log.e("MyTag","Roadworks size is " + size);
                    }
                }

                eventType = xpp.next();
            }
        }
        catch (XmlPullParserException ae1)
        {
            Log.e("MyTag","Parsing error" + ae1.toString());
        }
        catch (IOException ae1)
        {
            Log.e("MyTag","IO error during parsing");
        }



////////////////////////////////////////////////////////////////////////////////////////////////////




        System.out.println(alist);
        System.out.println(alist_2);
        System.out.println(alist_3);

        ma.runOnUiThread(new Runnable()
        {
            public void run() {

                Log.d("UI thread", "I am the UI thread");

                StringBuilder builder = new StringBuilder();
                for(CurrentIncidents incident : alist){

                    if(incident.getTitle().contains(search.toUpperCase())){
                        builder.append(incident.getTitle()).append("\n").
                                append(incident.getLatitude()).append("\n").
                                append(incident.getLongitude()).append("\n").
                                append(incident.getPublishDate()).append("\n\n");

                    }
                }
                for(Roadworks roadworks : alist_2){

                    if(roadworks.getTitle().contains(search.toUpperCase())){
                        builder.append(roadworks.getTitle()).append("\n").
                                append(roadworks.getLatitude()).append("\n").
                                append(roadworks.getLongitude()).append("\n").
                                append(roadworks.getPublishDate()).append("\n\n");

                    }

                }
                for(PlannedRoadworks plannedroadworks : alist_3){

                    if(plannedroadworks.getTitle().contains(search.toUpperCase())){
                        builder.append(plannedroadworks.getTitle()).append("\n").
                                append(plannedroadworks.getLatitude()).append("\n").
                                append(plannedroadworks.getLongitude()).append("\n").
                                append(plannedroadworks.getPublishDate()).append("\n\n");


                    }
                }

                acknowledgement.setText(builder.toString());
            }
        });

    }



////////////////////////////////////////////////////////////////////////////////////////////////////



    public void parseIncidents(MainActivity ma, String incidentData){

        acknowledgement = ma.findViewById(R.id.acknowledgement);
        alist = null;
        CurrentIncidents incident = new CurrentIncidents();

        try
        {
            System.out.println(incidentData);

            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(true);
            XmlPullParser xpp = factory.newPullParser();
            xpp.setInput( new StringReader( incidentData ) );
            int eventType = xpp.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT)
            {

                if(eventType == XmlPullParser.START_TAG)
                {
                    System.out.println("This is the start tag");
                    if (xpp.getName().equalsIgnoreCase("channel"))
                    {
                        alist  = new LinkedList<CurrentIncidents>();
                    }
                    else if (xpp.getName().equalsIgnoreCase("item"))
                    {
                        incident = new CurrentIncidents();
                    }
                    else if (xpp.getName().equalsIgnoreCase("title"))
                    {
                        String temp = xpp.nextText();
                        incident.setTitle(temp);
                    }
                    else if (xpp.getName().equalsIgnoreCase("point"))
                    {
                        String temp = xpp.nextText();

                        incident.setCoordinates(temp);
                        incident.splitCoords(temp);
                    }
                    else
                    if (xpp.getName().equalsIgnoreCase("pubDate"))
                    {
                        String temp = xpp.nextText();
                        incident.setPublishDate(temp);
                    }
                }

                else if(eventType == XmlPullParser.END_TAG)
                {
                    if (xpp.getName().equalsIgnoreCase("item"))
                    {
                        Log.e("MyTag","Incident is " + incident.toString());
                        alist.add(incident);
                    }
                    else if (xpp.getName().equalsIgnoreCase("channel"))
                    {
                        int size;
                        size = alist.size();
                        Log.e("MyTag","Roadworks size is " + size);
                    }
                }

                eventType = xpp.next();
            } // End of while
        }
        catch (XmlPullParserException ae1)
        {
            Log.e("MyTag","Parsing error" + ae1.toString());
        }
        catch (IOException ae1)
        {
            Log.e("MyTag","IO error during parsing");
        }
        System.out.println("End document");


        ma.runOnUiThread(new Runnable()
        {
            public void run() {
                Log.d("UI thread", "I am the UI thread");

                StringBuilder builder = new StringBuilder();

                if(alist.isEmpty()){

                    System.out.println(alist);
                    acknowledgement.setText("No incidents to show.");
                }else{
                    for(CurrentIncidents incident : alist){
                        builder.append(incident.getTitle()).append("\n").
                                append(incident.getLatitude()).append("\n").
                                append(incident.getLongitude()).append("\n").
                                append(incident.getPublishDate()).append("\n\n");
                    }
                    acknowledgement.setText(builder.toString());
                }
            }
        });
    }



////////////////////////////////////////////////////////////////////////////////////////////////////



    public void parseRoadworks(MainActivity ma, String roadworkData){
        //roadworks are parsed if the user clicks on the raodworks button
        acknowledgement = ma.findViewById(R.id.acknowledgement);
        alist_2 = null;
        Roadworks roadwork = new Roadworks();


        try
        {
            System.out.println(roadworkData);

            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(true);
            XmlPullParser xpp = factory.newPullParser();
            xpp.setInput( new StringReader( roadworkData ) );
            int eventType = xpp.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT)
            {

                if(eventType == XmlPullParser.START_TAG)
                {
                    System.out.println("This is the start tag");
                    if (xpp.getName().equalsIgnoreCase("channel"))
                    {
                        alist_2 = new LinkedList<Roadworks>();
                    }
                    else if (xpp.getName().equalsIgnoreCase("item"))
                    {
                        roadwork = new Roadworks();
                    }
                    else if (xpp.getName().equalsIgnoreCase("title"))
                    {
                        String temp = xpp.nextText();
                        roadwork.setTitle(temp);
                    }
                    else if (xpp.getName().equalsIgnoreCase("point"))
                    {
                        String temp = xpp.nextText();

                        roadwork.setCoordinates(temp);
                        roadwork.splitCoords(temp);
                    }
                    else
                    if (xpp.getName().equalsIgnoreCase("pubDate"))
                    {
                        String temp = xpp.nextText();
                        roadwork.setPublishDate(temp);
                    }
                }

                else if(eventType == XmlPullParser.END_TAG)
                {
                    if (xpp.getName().equalsIgnoreCase("item"))
                    {
                        Log.e("MyTag","Incident is " + roadwork.toString());
                        alist_2.add(roadwork);
                    }
                    else if (xpp.getName().equalsIgnoreCase("channel"))
                    {
                        int size;
                        size = alist_2.size();
                        Log.e("MyTag","Roadworks size is " + size);
                    }
                }

                eventType = xpp.next();
            }
        }
        catch (XmlPullParserException ae1)
        {
            Log.e("MyTag","Parsing error" + ae1.toString());
        }
        catch (IOException ae1)
        {
            Log.e("MyTag","IO error during parsing");
        }
        System.out.println("End document");


        ma.runOnUiThread(new Runnable()
        {
            public void run() {
                Log.d("UI thread", "I am the UI thread");

                StringBuilder builder = new StringBuilder();

                if(alist_2==null){
                    System.out.println(alist_2);
                    acknowledgement.setText("No roadworks to show.");
                }else{
                    for(Roadworks roadworks : alist_2){
                        builder.append(roadworks.getTitle()).append("\n").
                                append(roadworks.getLatitude()).append("\n").
                                append(roadworks.getLongitude()).append("\n").
                                append(roadworks.getPublishDate()).append("\n\n");

                    }
                    acknowledgement.setText(builder.toString());
                }
            }
        });
    }



////////////////////////////////////////////////////////////////////////////////////////////////////



    public void parsePlannedRoadworks(MainActivity ma, final String plannedRoadworkData){

        acknowledgement = ma.findViewById(R.id.acknowledgement);
        alist_3 = null;
        PlannedRoadworks plannedRw = new PlannedRoadworks();
        try
        {
            System.out.println(plannedRoadworkData);

            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(true);
            XmlPullParser xpp = factory.newPullParser();
            xpp.setInput( new StringReader( plannedRoadworkData ) );
            int eventType = xpp.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT)
            {

                if(eventType == XmlPullParser.START_TAG)
                {
                    System.out.println("This is the start tag");
                    if (xpp.getName().equalsIgnoreCase("channel"))
                    {
                        alist_3 = new LinkedList<PlannedRoadworks>();
                    }
                    else if (xpp.getName().equalsIgnoreCase("item"))
                    {
                        plannedRw = new PlannedRoadworks();
                    }
                    else if (xpp.getName().equalsIgnoreCase("title"))
                    {
                        String temp = xpp.nextText();
                        plannedRw.setTitle(temp);
                    }
                    else if (xpp.getName().equalsIgnoreCase("point"))
                    {
                        String temp = xpp.nextText();

                        plannedRw.setCoordinates(temp);
                        plannedRw.splitCoords(temp);
                    }
                    else
                    if (xpp.getName().equalsIgnoreCase("pubDate"))
                    {

                        String temp = xpp.nextText();

                        plannedRw.setPublishDate(temp);
                    }
                }

                else if(eventType == XmlPullParser.END_TAG)
                {
                    if (xpp.getName().equalsIgnoreCase("item"))
                    {
                        Log.e("MyTag","Incident is " + plannedRw.toString());
                        alist_3.add(plannedRw);
                    }
                    else if (xpp.getName().equalsIgnoreCase("channel"))
                    {
                        int size;
                        size = alist_3.size();
                        Log.e("MyTag","Roadworks size is " + size);
                    }
                }

                eventType = xpp.next();
            }
        }
        catch (XmlPullParserException ae1)
        {
            Log.e("MyTag","Parsing error" + ae1.toString());
        }
        catch (IOException ae1)
        {
            Log.e("MyTag","IO error during parsing");
        }
        System.out.println("End document");


        ma.runOnUiThread(new Runnable()
        {
            public void run() {
                Log.d("UI thread", "I am the UI thread");

                StringBuilder builder = new StringBuilder();

                if(alist_3==null){
                    System.out.println(alist_3);
                    acknowledgement.setText("No planned roadworks to show.");
                }else{
                    for(PlannedRoadworks plannedroadworks : alist_3){
                        builder.append(plannedroadworks.getTitle()).append("\n").
                                append(plannedroadworks.getLatitude()).append("\n").
                                append(plannedroadworks.getLongitude()).append("\n").
                                append(plannedroadworks.getPublishDate()).append("\n\n");
                    }
                    acknowledgement.setText(builder.toString());
                }
            }
        });
    }
}

