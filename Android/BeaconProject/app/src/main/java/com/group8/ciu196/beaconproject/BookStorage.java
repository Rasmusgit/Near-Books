package com.group8.ciu196.beaconproject;


import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.Iterator;

public class BookStorage {

    private static final String TAG = "BookStorage";
    private static BookStorage bookStorageInstance = null;
    private static ArrayList<Book> books = new ArrayList<Book>();


    String arc = "{\n" +
            "  \"9780711239234\": {\n" +
            "    \"name\": \"Women Design: Pioneers in architecture, industrial, graphic\",\n" +
            "    \"author\": \"Libby Sellers\",\n" +
            "    \"availability\": \"1\",\n" +
            "    \"origin\": \"Sellers, Libby Author.\",\n" +
            "    \"shelf\": \"-\",\n" +
            "    \"publication\": \"2018\",\n" +
            "    \"imageStr\": \"arc0\"\n" +
            "  },\n" +
            "  \"9783791382784\": {\n" +
            "    \"name\": \"New architecture London\",\n" +
            "    \"author\": \"Agnese Sanvito, Richard Schulman\",\n" +
            "    \"availability\": \"2\",\n" +
            "    \"origin\": \"-\",\n" +
            "    \"shelf\": \"Ic\",\n" +
            "    \"publication\": \"2017\",\n" +
            "    \"imageStr\": \"arc1\"\n" +
            "  },\n" +
            "  \"9780500343166\": {\n" +
            "    \"name\": \"Adjaye · Africa · Architecture\",\n" +
            "    \"author\": \"David Adjaye\",\n" +
            "    \"availability\": \"1\",\n" +
            "    \"origin\": \"Adjaye, David Author.\",\n" +
            "    \"shelf\": \"Ic.6\",\n" +
            "    \"publication\": \"2016\",\n" +
            "    \"imageStr\": \"arc2\"\n" +
            "  },\n" +
            "  \"9780996539326\": {\n" +
            "    \"name\": \"Sketchup & Layout for architecture: the step by step workflow of Nick Sonder\",\n" +
            "    \"author\": \"Matt Donley, Nick Sonder\",\n" +
            "    \"availability\": \"2\",\n" +
            "    \"origin\": \"Donley, Matt\",\n" +
            "    \"shelf\": \"Ic\",\n" +
            "    \"publication\": \"2016\",\n" +
            "    \"imageStr\": \"arc3\"\n" +
            "  },\n" +
            "  \"9780300203547\": {\n" +
            "    \"name\": \"Romanesque architecture\",\n" +
            "    \"author\": \"Eric Fernie\",\n" +
            "    \"availability\": \"10023\",\n" +
            "    \"origin\": \"Donley, Matt\",\n" +
            "    \"shelf\": \"Ic\",\n" +
            "    \"publication\": \"2014\",\n" +
            "    \"imageStr\": \"arc4\"\n" +
            "  }\n" +
            "}";

    String sci = "{\n" +
            "  \"9780857666611\": {\n" +
            "    \"name\": \"Stars are legion\",\n" +
            "    \"author\": \"Kameron Hurley\",\n" +
            "    \"availability\": \"3\",\n" +
            "    \"origin\": \"Hurley, Kameron aut\",\n" +
            "    \"shelf\": \"He Engelska\",\n" +
            "    \"publication\": \"Original issue 2017\",\n" +
            "    \"description\": \"But soon the fledgling Foundation finds itself at the mercy of corrupt warlords rising in the wake of the receding Empire. And mankind’s last best hope is faced with an agonizing choice: submit to the barbarians and live as slaves—or take a stand for freedom and risk total destruction.\\\\nSomewhere on the outer rim of the universe, a mass of decaying world-ships known as the Legion is traveling in the seams between the stars. Here in the darkness, a war for control of the Legion has been waged for generations, with no clear resolution.\\\\nZan wakes with no memory, prisoner of a people who say there are her family. She is told she is their salvation, the only person capable of boarding the Mokshi, a world-ship with the power to leave the Legion. But Zan’s new family is not the only one desperate to gain control of the prized ship. Zan finds that she must choose sides in a genocidal campaign that will take her from the edges of the Legion’s gravity well to the very belly of the world.\\\\nIn the tradition of Iain M. Banks’s Culture novels and Roger Zelazny’s Chronicles of Amber, Kameron Hurley has created an epic and thrilling tale about tragic love, revenge, and war as imagined by one of our most celebrated new writers.\",\n" +
            "    \"img\": \"stars\"\n" +
            "  },\n" +
            "  \"9780316435260\": {\n" +
            "    \"name\": \"Places in the darkness\",\n" +
            "    \"author\": \"Brookmyre christopher, 1968\",\n" +
            "    \"availability\": \"0\",\n" +
            "    \"origin\": \"Brookmyre christopher, 1968 author.\",\n" +
            "    \"shelf\": \"He\",\n" +
            "    \"publication\": \"Original issue 2017\",\n" +
            "    \"Description\": \"Hundreds of miles above Earth, the space station Ciudad de Cielo--The City in the Sky--is a beacon of hope for humanity's expansion into the stars. But not everyone aboard shares such noble ideals. \\\\nBootlegging, booze, and prostitution form a lucrative underground economy for rival gangs, which the authorities are happy to turn a blind eye to until a disassembled corpse is found dancing in the micro-gravity. \\\\nIn charge of the murder investigation is Nikki \\\"Fix\\\" Freeman, who is not thrilled to have Alice Blake, an uptight government goody-two-shoes, riding shotgun. As the bodies pile up, and the partners are forced to question their own memories, Nikki and Alice begin to realize that gang warfare may not be the only cause for the violence.\",\n" +
            "    \"img\": \"darkness\"\n" +
            "  },\n" +
            "  \"9788499890944\": {\n" +
            "    \"name\": \"1984\",\n" +
            "    \"author\": \"Orwell, George\",\n" +
            "    \"availability\": \"1\",\n" +
            "    \"origin\": \"Orwell, George 1903-1950\",\n" +
            "    \"shelf\": \"Hk Spanska\",\n" +
            "    \"publication\": \"Original issue 2013\",\n" +
            "    \"Description\": \"Winston Smith toes the Party line, rewriting history to satisfy the demands of the Ministry of Truth. With each lie he writes, Winston grows to hate the Party that seeks power for its own sake and persecutes those who dare to commit thoughtcrimes. But as he starts to think for himself, Winston can’t escape the fact that Big Brother is always watching...\\\\nA startling and haunting vision of the world, 1984 is so powerful that it is completely convincing from start to finish. No one can deny the influence of this novel, its hold on the imaginations of multiple generations of readers, or the resiliency of its admonitions—a legacy that seems only to grow with the passage of time.\",\n" +
            "    \"img\": \"a1984\"\n" +
            "  },\n" +
            "  \"9780804190145\": {\n" +
            "    \"name\": \"Ready Player One\",\n" +
            "    \"author\": \"Cline, Ernest\",\n" +
            "    \"availability\": \"5\",\n" +
            "    \"origin\": \"Cline, Ernest\",\n" +
            "    \"shelf\": \"He Engelska\",\n" +
            "    \"publication\": \"Original issue 2018\",\n" +
            "    \"Description\": \"In the year 2045, reality is an ugly place. The only time teenage Wade Watts really feels alive is when he's jacked into the virtual utopia known as the OASIS. Wade's devoted his life to studying the puzzles hidden within this world's digital confines—puzzles that are based on their creator's obsession with the pop culture of decades past and that promise massive power and fortune to whoever can unlock them. \\\\nBut when Wade stumbles upon the first clue, he finds himself beset by players willing to kill to take this ultimate prize. The race is on, and if Wade's going to survive, he'll have to win—and confront the real world he's always been so desperate to escape.\",\n" +
            "    \"img\": \"readyplayer\"\n" +
            "  },\n" +
            "  \"9780553803716\": {\n" +
            "    \"name\": \"Foundation\",\n" +
            "    \"author\": \"Asimov, Isaac\",\n" +
            "    \"availability\": \"3\",\n" +
            "    \"origin\": \"Asimov, Isaac\",\n" +
            "    \"shelf\": \"He Engelska\",\n" +
            "    \"publication\": \"Original issue 2014\",\n" +
            "    \"Description\": \"But when Wade stumbles upon the first clue, he finds himself beset by players willing to kill to take this ultimate prize. The race is on, and if Wade's going to survive, he'll have to win—and confront the real world he's always been so desperate to escape.\\\\nBut soon the fledgling Foundation finds itself at the mercy of corrupt warlords rising in the wake of the receding Empire. And mankind’s last best hope is faced with an agonizing choice: submit to the barbarians and live as slaves—or take a stand for freedom and risk total destruction.\",\n" +
            "    \"img\": \"foundation\"\n" +
            "  },\n" +
            "  \"9781484780787\": {\n" +
            "    \"name\": \"Leia, Princess of Alderaan\",\n" +
            "    \"author\": \"Gray, Claudia\",\n" +
            "    \"availability\": \"2\",\n" +
            "    \"origin\": \"Gray, Claudia aut\",\n" +
            "    \"shelf\": \"He,u Engelska\",\n" +
            "    \"publication\": \"Original issue 2017\",\n" +
            "    \"Description\": \"The never-before-told story of how young Leia Organa comes to join the rebellion against the evil Empire, from best-selling author Claudia Gray.\",\n" +
            "    \"img\": \"leia_princess_of_alderaan_new_cover\"\n" +
            "  }\n" +
            "}";


    String music = "{\n" +
            "  \"8780857666611\": {\n" +
            "    \"name\": \"Phantom Pop\",\n" +
            "    \"author\": \"Wild Party\n\",\n" +
            "    \"availability\": \"3\",\n" +
            "    \"origin\": \"Fearless Records\",\n" +
            "    \"shelf\": \"He Engelska\",\n" +
            "    \"publication\": \"7 oktober 2014\",\n" +
            "    \"description\": \"Indie/Alternativ\",\n" +
            "    \"img\": \"phantompop\"\n" +
            "  },\n" +
            "  \"8780316435260\": {\n" +
            "    \"name\": \"Pine Trails\",\n" +
            "    \"author\": \"Satellite Stories\",\n" +
            "    \"availability\": \"0\",\n" +
            "    \"origin\": \"Cargo Records\",\n" +
            "    \"shelf\": \"He\",\n" +
            "    \"publication\": \"1 november 2013\",\n" +
            "    \"Description\": \"Indie/Alternativ\",\n" +
            "    \"img\": \"pinetrails\"\n" +
            "  },\n" +
            "  \"8788499890944\": {\n" +
            "    \"name\": \"The Visitors\",\n" +
            "    \"author\": \"Abba\",\n" +
            "    \"availability\": \"1\",\n" +
            "    \"origin\": \"Benny Andersson, Björn Ulvaeus\",\n" +
            "    \"shelf\": \"Hk Spanska\",\n" +
            "    \"publication\": \"30 november 1981\",\n" +
            "    \"Description\": \"The Visitors är det åttonde och sista studioalbumet av den svenska popgruppen ABBA och släppes den 30 november 1981. Mindre än ett år senare spelade de in sin sista gemensamma sång. Albumet producerades av Benny Andersson och Björn Ulvaeus, som även skrev samtliga sånger på albumet. Albumet släpptes 1982 även på CD\",\n" +
            "    \"img\": \"thevisitors\"\n" +
            "  },\n" +
            "  \"8780804190145\": {\n" +
            "    \"name\": \"Achter Tag\",\n" +
            "    \"author\": \"Genetikk\",\n" +
            "    \"availability\": \"5\",\n" +
            "    \"origin\": \"Selfmade Records\",\n" +
            "    \"shelf\": \"He Engelska\",\n" +
            "    \"publication\": \"24 april 2015\",\n" +
            "    \"Description\": \"Hiphop/rap\",\n" +
            "    \"img\": \"achtertag\"\n" +
            "  },\n" +
            "  \"8780553803716\": {\n" +
            "    \"name\": \"In Search of Elusive Little Comets\",\n" +
            "    \"author\": \"Little Comets\",\n" +
            "    \"availability\": \"3\",\n" +
            "    \"origin\": \"Michael Coles\",\n" +
            "    \"shelf\": \"He Engelska\",\n" +
            "    \"publication\": \"31 januari 2011\",\n" +
            "    \"Description\": \"But when Wade stumbles upon the first clue, he finds himself beset by players willing to kill to take this ultimate prize. The race is on, and if Wade's going to survive, he'll have to win—and confront the real world he's always been so desperate to escape.\\\\nBut soon the fledgling Foundation finds itself at the mercy of corrupt warlords rising in the wake of the receding Empire. And mankind’s last best hope is faced with an agonizing choice: submit to the barbarians and live as slaves—or take a stand for freedom and risk total destruction.\",\n" +
            "    \"img\": \"insearchofelusivelittlecomets\"\n" +
            "  },\n" +
            "  \"8781484780787\": {\n" +
            "    \"name\": \"You Haunt Me\",\n" +
            "    \"author\": \"Sir Sly\",\n" +
            "    \"availability\": \"2\",\n" +
            "    \"origin\": \"Cherrytree/Interscope Records\",\n" +
            "    \"shelf\": \"He,u Engelska\",\n" +
            "    \"publication\": \"16 september 2014\",\n" +
            "    \"Description\": \"Indiepop, Trance\",\n" +
            "    \"img\": \"youhauntme\"\n" +
            "  }\n" +
            "}";

    public static BookStorage getInstance(){
        if(bookStorageInstance == null){
            bookStorageInstance = new BookStorage();
        }
        return bookStorageInstance;
    }

    public String getArchitectureBooks(){
        return arc;
    }

    public String getSciFiBooks(){
        return sci;
    }

    public String getMusic(){
        return music;
    }


    public ArrayList<Book> jsonToBook(){

        ArrayList<Book> books = new ArrayList<Book>();


        try {

            JSONObject jObjArc = new JSONObject(getArchitectureBooks());
            Iterator<String> namesArc = jObjArc.keys();


            while (namesArc.hasNext()) {


                String isbn = namesArc.next();
                String jItem = jObjArc.getString(isbn);
                JSONObject k2 = new JSONObject(jItem);

                String title = k2.getString("name");
                String author = k2.getString("author");
                int availability = Integer.parseInt(k2.getString("availability"));
                String shelf = k2.getString("shelf");
                String origin = k2.getString("origin");
                String publication = k2.getString("publication");
                String imageStr = k2.getString("imageStr");


                books.add(new Book(isbn, title, author,availability,origin,shelf,publication,"Architecture",imageStr));

            }

            JSONObject jObjSci = new JSONObject(getSciFiBooks());
            Iterator<String> namesSci = jObjSci.keys();

            while (namesSci.hasNext()) {


                String isbn = namesSci.next();
                String jItem = jObjSci.getString(isbn);
                JSONObject k2 = new JSONObject(jItem);

                String title = k2.getString("name");
                String author = k2.getString("author");
                int availability = Integer.parseInt(k2.getString("availability"));
                String shelf = k2.getString("shelf");
                String origin = k2.getString("origin");
                String publication = k2.getString("publication");
                String imageStr = k2.getString("img");


                books.add(new Book(isbn, title, author,availability,origin,shelf,publication,"Sci-fi",imageStr));

            }


            JSONObject jObjMusic = new JSONObject(getMusic());
            Iterator<String> namesMusic = jObjMusic.keys();

            while (namesMusic.hasNext()) {


                String isbn = namesMusic.next();
                String jItem = jObjMusic.getString(isbn);
                JSONObject k2 = new JSONObject(jItem);

                String title = k2.getString("name");
                String author = k2.getString("author");
                int availability = Integer.parseInt(k2.getString("availability"));
                String shelf = k2.getString("shelf");
                String origin = k2.getString("origin");
                String publication = k2.getString("publication");
                String imageStr = k2.getString("img");


                books.add(new Book(isbn, title, author,availability,origin,shelf,publication,"Music",imageStr));

            }




        } catch (JSONException e) {

            e.printStackTrace();

        }


        return books;
    }




}
