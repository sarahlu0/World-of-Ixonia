import java.util.*;
import java.util.concurrent.TimeUnit; 

public class Game {
	
	private Player player;
	private boolean playGame;
	private DialogueSystem dialogueSystem;
	private GameMap worldMap;
	
	public Game() {
		this.player = new Player("Traveler");
		this.playGame = true;
		this.dialogueSystem = new DialogueSystem();
		this.worldMap = new GameMap(50, 50); // can be changed to increase world size
		
		initiateMap(worldMap);
		player.setGameMap(worldMap);
	}
	
	
	// SETS UP EVERYTHING - MAP LOCATIONS (+ ITEMS), NPCS, DIALOGUE 
	private void initiateMap(GameMap worldMap) {

		
		// ************ AUTUMN VILLAGE ************
		
		Location park = new Location("park", "You are in a park. The grass is green and lush, and comfortable to lay on. The sun is shining on you, casting a warm and pleasant glow. The sky is blue and you can hear birds chirping. It is a nice day. To the east, there seems to be a large, dark wooden building. To the south, you can see an open square.", 10, 10);
		Location townSquare =  new Location ("town square", "You are in the town square. In front of you is a beautiful fountain. The water casts a rainbow when it hits the sunlight. It seems to be the center of the village. If you look in the fountain, you can see at the bottom there is a small square rune that is displaced from the bottom of the fountain. To the north, you see a large expanse of green grass. To the east, you see a large open space surrounded by buildings and market stalls. To the south, you see the bright glow of lava coming from a furnace and see the plume of smoke rising above.", 11, 10);
		Location forge = new Location("forge", "You are standing in front of a forge. It is built with dark material with a line of stoves. The furnace is alight with flames. There is a young man standing near the furnace, wearing an apron. He wears a name tag that says \"Mason.\" To the north, you see an open square. To the east, you see a humble, light wooden building.", 12, 10);
		Location tavern = new Location ("tavern", "You are standing in front of a large, dark oak tavern. The light inside glows warm and inviting. You can hear chatter coming from inside. To to the east you see a large expanse of green grass. To the south, you see a large open space surrounded by buildings and market stalls.", 10, 11);
		Location marketplace =  new Location("marketplace", "You are standing in a large, flat and open space filled with various shops and stalls selling trinkets, clothing, merchandise, and a lot of food. Various people are bustling out and about, filling the entire area with a lively and energetic atmosphere. To the north, you see a large, dark wooden building beyond the market stalls. You see a stall filled to the brim with jewelry and other trinkets, and there is a young woman with a big smile manning it. She wears a name tag that says \"Michelle.\" There is also another merchant to the side. To the south, you see a humble, light wooden building. To the east, you see an open square with a fountain.", 11, 11);
		Location inn = new Location("inn", "You are standing in front of a humble inn. There is a hanging sign above the door, with the first line smudged so darkly that you can't make out the words, but the second line says \'Izakaya.\'", 12, 11);
		Location innRoom = new Location ("inn room", "You are standing in a small room on the second floor of the inn. It is furnished simply with a closet, drawer, a table and a chair, a lamp, and one bed.", 17, 7);
		Location innInside = new Location("inn", "You are standing inside a humble inn. There are some tables and chairs made of nice wood, and a bar desk where the innkeeper is standing behind. On the side of one of the tables, there is an inn room key, seemingly left unattended. There are stairs that lead upwards.", 16, 10);
		Location tavernInside = new Location("tavern inside", "You step into the tavern, greeted by the warm glow of flickering candles and a roaring hearth. The air is rich with the aroma of roasted meats, fresh bread, and spiced ale. Wooden tables, worn smooth by time, fill the room, each surrounded by groups of lively patrons. Tapestries depicting epic battles and legendary creatures adorn the walls. Behind the bar, a friendly barkeep pours drinks for the crowd. Laughter and the clinking of mugs create a lively, welcoming atmosphere. By one of the tables, there sits a fox woman named Yuli.", 17, 9);
		Location innHallway = new Location("inn hallway", "You walk up the stairs into the hallway of the inn. It is small and cramped, with a window that lets in a sliver of light. There is a door in the hallway that you might enter.", 15 ,10);

		worldMap.setLocation(park, 10, 10);
		worldMap.setLocation(townSquare, 11, 10);
		worldMap.setLocation(forge, 12, 10);
		worldMap.setLocation(marketplace, 11, 11);
	    worldMap.setLocation(tavern, 10, 11);
	    worldMap.setLocation(inn, 12, 11);
	    worldMap.setLocation(innHallway, 15, 10);
	    worldMap.setLocation(innInside, 16, 10);
	    worldMap.setLocation(tavernInside, 17, 9);
	    worldMap.setLocation(innRoom, 17, 7);

	    // set enter and exit locations
	    inn.setEnterLocation(innInside);
	    innInside.setExitLocation(inn);
	    tavern.setEnterLocation(tavernInside);
	    tavernInside.setExitLocation(tavern);
		innHallway.setEnterLocation(innRoom);
		innRoom.setExitLocation(innHallway);
	    
	    
	    // set accessibility
	    innRoom.setAccessible(false);
	    innRoom.setRequiredItem("Inn Room Key");
	    
	    // add items to locations
	    townSquare.addItem(new Item("Square Rune", "A small, square shaped stone piece that has an unknown engraving on it. It glows a faint pink color that is very barely noticable in the light."));
		park.addItem(new Item("Bird", "A small brown bird with black tipped feathers. It trembles in your hands.", true));
		innInside.addItem(new Item("Inn Room Key", "A rusty black metal key.", true));
		
		// ********* PATHS *************
	    
		Location pathw1 = new Location("path", "You walk along a dirt path. It seems well-worn, with tracks of hooves and wheel marks in the ground. To the west, you see a dark, wooden building. To the east, the path continues into a forest.", 10, 12);
		Location pathw2 = new Location("path", "You walk along the dirt path into the forest. Tall tress surround you. The path diverges into two routes: north and south. To the north, you can make the faint trace of a mountain range beyond the forest. The path that way seems fainter and less traveled. To the south, you see a long path that cuts through the forest. You can see specks of gravel along that path.", 10, 13);
		Location pathw3 = new Location("path", "You walk along the path, and it is slowly becoming fainter. It curves along to the east. Other than the path, you cannot see any openings in the forest.", 9, 13);
		Location pathw4 = new Location("path", "You walk along the path. It becomes fainter until the path disappears altogether. To the east, you see the edge of a stone edifice. Unable to make out what it is, you suddenly feel very uneasy. It feels like you've walked into a place you should not be.", 9, 14);
		Location pathw5 = new Location("path", "You walk along the path. It slopes downward. It continues to the south.", 11, 13);
		Location pathw6 = new Location("path", "You walk along the path. There seems to be a clearing eastward, with a small opening in the trees, and a faint light glows from that direction. The path continues south.", 12, 13);
		Location pathw7 = new Location("path", "You walk further along the path. The forest starts to lightens up. The path continues south.", 13, 13);
		Location pathw8 = new Location("path", "You walk along the path. It starts to become a mix of dirt, gravel, and specks of sand, with patches of short grass. You are almost completely out of the forest. To the south, you can see the start of a sandy shore where the forest ends.", 14, 13);

		worldMap.setLocation(pathw1, 10, 12);
		worldMap.setLocation(pathw2, 10, 13);
		worldMap.setLocation(pathw3, 9, 13);
		worldMap.setLocation(pathw4, 9, 14);
		worldMap.setLocation(pathw5, 11, 13);
		worldMap.setLocation(pathw6, 12, 13);
		worldMap.setLocation(pathw7, 13, 13);
		worldMap.setLocation(pathw8, 14, 13);
		
		// wilderness

		Location ruins = new Location("ruins", "You stand amidst ancient, crumbling ruins, where glowing runes and enchanted vines whisper secrets of long-lost magic. You feel unnerved with an uncomfortable feeling, like you are being watched. There is a dark figure standing at the edge of the ruins, and he does not seem to notice you.", 9, 15);
		Location isaCabin = new Location("cabin in the woods", "You stand before a cozy cabin in the woods, its warm light spilling out into the forest, surrounded by the scent of pine and the sound of rustling leaves.", 12, 14);
		Location isaInside = new Location("cabin in the woods", "You step inside the cozy cabin, greeted by the warmth of a crackling  fireplace and the inviting aroma of freshly baked bread from the quaint, rustic kitchen.", 17, 11);
		Location beach = new Location("beach", "You stand on a beach, the warm sand beneath your feet and the gentle waves kissing the shore, feeling the salty breeze and the sun's embrace.", 15, 13);

		worldMap.setLocation(ruins, 9, 15);
		worldMap.setLocation(isaCabin, 12, 14);
		worldMap.setLocation(isaInside, 17, 11);
		worldMap.setLocation(beach, 15, 13);

	    isaCabin.setEnterLocation(isaInside);
	    isaInside.setExitLocation(isaCabin);
		// aspen kingdom
		
		
		
		
		// ocean
		
		
		
		// longshan mountians
		
		
		
	    

	    // NPC DIALOGUE SET UP
		
		// Michelle
		DialoguePiece mich1 = new DialoguePiece("Hi! Anything here interests you?");
		DialoguePiece mich2 = new DialoguePiece("Ooh, you have a taste! This is a beautiful pendant indeed, with a fine gem harvested from the gleaming waterfall. It surely has great purity!");
		DialoguePiece mich3 = new DialoguePiece("Never heard of it? It's a magical place far far away, the stuff of legends that only worthy travelers are able to reach and benefit from its riches. I actually have never been there, haha, but I get some of my wares from traveling merchants, and some of them have been there! \n Anyways, if you want this pendant, it will be 2 copper!");
		DialoguePiece mich4 = new DialoguePiece("Bye!");

		DialoguePiece mich5 = new DialoguePiece("Thanks! Here you go!");
		DialoguePiece mich6 = new DialoguePiece("That's alright, bye.");
		
		
		DialogueOption omich1 = new DialogueOption("What about that sapphire pendant?", mich2);
		mich1.addOption(omich1);
//		DialogueOption omich2 = new DialogueOption("What about that jade bracelet?", mich5);
//		mich1.addOption(omich2);
		DialogueOption omich3 = new DialogueOption("What is the gleaming waterfall?", mich3);
		mich2.addOption(omich3);
		DialogueOption omich4 = new DialogueOption("No, bye.", mich4);
		mich1.addOption(omich4);
		

		DialogueOption omich5 = new DialogueOption("Sure, I'll purchase the sapphire pendant.", mich5);
		DialogueOption omich6 = new DialogueOption("Ah, no thanks.", mich6);

		mich3.addOption(omich6);
		mich3.addOption(omich5);
		

		NPC michelle = new NPC("Michelle", "A shopkeeper with a friendly smile on her face. She has long blond hair and golden-brown eyes. She wears a simple plain white blouse and dark pants.", mich1);
		
		marketplace.addNPC(michelle);
		
		// Merchant
		DialoguePiece merch1 = new DialoguePiece("Hello there. I like buying stuff. Do you have anything to sell to me?");
		DialoguePiece merch2 = new DialoguePiece("I will give you 1 copper for that.");
		DialoguePiece merch3 = new DialoguePiece("I will give you 2 coppers for that.");
		DialoguePiece merch4 = new DialoguePiece("Thank you, here is your copper for your bird feather.");
		DialoguePiece merch7 = new DialoguePiece("Thank you, here is your copper for your leather boots.");
		DialoguePiece merch5 = new DialoguePiece("Bye!");
		DialoguePiece merch6 = new DialoguePiece("Okay, no problem.");

		DialogueOption omerch1 = new DialogueOption("I have these leather boots.", merch3);
		DialogueOption omerch2 = new DialogueOption("I have a bird feather.", merch2);
		DialogueOption omerch3 = new DialogueOption("No, I don't. Bye.", merch5);

		merch1.addOption(omerch1);
		merch1.addOption(omerch2);
		merch1.addOption(omerch3);


		DialogueOption omerch4 = new DialogueOption("Okay, I will give you a bird feather for 1 copper.", merch4);
		DialogueOption omerch5 = new DialogueOption("No thanks.", merch6);

		DialogueOption omerch6 = new DialogueOption("Okay, I will give you these leather boots for 2 copper.", merch7);
		DialogueOption omerch7 = new DialogueOption("No thanks.", merch6);

		merch2.addOption(omerch4);
		merch2.addOption(omerch5);
		merch3.addOption(omerch6);
		merch3.addOption(omerch7);
		
		
		NPC merchant = new NPC("Merchant", "A plain-looking merchant in brown leather.", merch1);
		
		marketplace.addNPC(merchant);
		
		
		// Bartender 
		DialoguePiece bar1 = new DialoguePiece("Hey there. Ya want a drink?");
		DialoguePiece bar2 = new DialoguePiece("Well, you've come at a prime time! The Harvest Festival has just started. If you go to the marketplace, you'll see a ton of shops and people bustlin' about.");
		DialoguePiece bar3 = new DialoguePiece("It's an annual festival hosted here in the Autumn Village that celebrates our annual good harvest and connection with the Aspen Kingdom. It lasts a few days. People take off work for the most part and there are a lot of fun activities and eents, and tons of food and trinkets to buy. Sometimes, knights and nobles from the kingdom arrive as well, but we haven't seen the Queen for a few years now.");
		DialoguePiece bar4 = new DialoguePiece("That'll be 1 copper.");
		
		
		// mason
		DialoguePiece mason1 = new DialoguePiece("Hey there! How can I help you?");
		
		
		NPC mason = new NPC("Mason", "A young man with sandy brown hair. Two giant brown wings with black and white stripes at the tips sprout from his back.", mason1);
		
		forge.addNPC(mason);
		
		// jade
		DialoguePiece jade1 = new DialoguePiece("Hello, traveler. It's a beautiful day, isn't it?");
		DialoguePiece jade2 = new DialoguePiece("Have you visited the marketplace yet? There's so many beautiful things being sold there. I particularly liked this one piece of jewelry, this pendant, see.. it's sapphire, and very beautiful. But I don't have any money.");

		DialogueOption ojade1 = new DialogueOption("Yes, it is. What are you doing here?", jade2);
		DialogueOption ojade2 = new DialogueOption("It's not what I particularly like.", jade2);

		jade1.addOption(ojade1);
		jade1.addOption(ojade2);

		NPC jade = new NPC("Jade", "A tall girl with a stoic demeanor, but a gentle and familiar presence. She has green hair and jade green eyes.", jade1);
		park.addNPC(jade);

		// purrok
		DialoguePiece purrok1 = new DialoguePiece("Ah, another lost soul in these ancient ruins. What brings you here?");
		DialoguePiece purrok2 = new DialoguePiece("Well, these parts are not for the ordinary traveler. You should leave.");

		DialogueOption opurrok1 = new DialogueOption("Just exploring. Who are you?", purrok2);
		DialogueOption opurrok2 = new DialogueOption("Looking for someone.", purrok2);

		purrok1.addOption(opurrok1);
		purrok1.addOption(opurrok2);

		NPC purrok = new NPC("Purrok", "An enigmatic figure wandering the ruins.", purrok1);
		
		ruins.addNPC(purrok);

		
		// amira
		
		
		// yuli 
		DialoguePiece yuli1 = new DialoguePiece("Hey there, traveler. My name is Yuli.");

		DialoguePiece yuli2 = new DialoguePiece("Well, I don't frequent human civilization much. But I came here because I heard about the festival and so I thought I'd come take a look.");
		DialogueOption oyuli1_1 = new DialogueOption("What are you doing here?", yuli2);

		DialoguePiece yuli3 = new DialoguePiece("I like sweet things, and the taste of beer. It is nice in my mouth and makes me feel warm and fuzzy inside.");
		DialogueOption oyuli1_1_1 = new DialogueOption("Why the tavern though?", yuli3);

		DialoguePiece yuli4 = new DialoguePiece("I've just never been around humans. I live in the wilderness, see, and I meet humans frequently. But I mostly lure them in so they lower their guard and then I eat their souls.");
		DialogueOption oyuli1_1_2 = new DialogueOption("You don't like being near humans or what?", yuli4);

		DialoguePiece yuli5 = new DialoguePiece("I'm just kidding! I wouldn't do that, but that sure does sound delicious... But I like meat enough so I don't need to do that.");
		DialogueOption oyuli1_1_2_1 = new DialogueOption("E-eat their souls??", yuli5);

		DialoguePiece yuli6 = new DialoguePiece("Bye!"); // Yuli smiles, closes her eyes, and waves
		DialogueOption oyuli1_1_2_2 = new DialogueOption("UM OK BYE", yuli6);

		DialoguePiece yuli7 = new DialoguePiece("Don't you have any manners? But I don't really care. I'm a kitsune lady, so I have a human form but I was born a kitsune.");
		DialogueOption oyuli1_2 = new DialogueOption("What are you?", yuli7);

		DialoguePiece yuli8 = new DialoguePiece("Yes, I can eat souls.");
		DialogueOption oyuli1_2_1 = new DialogueOption("Wow, that's cool. Do you have powers?", yuli8);

		DialoguePiece yuli9 = new DialoguePiece("A kitsune is a fox spirit with the ability to shape-shift into human form and other magical abilities. We are known in folklore to be tricksters and have various powers.");
		DialogueOption oyuli1_2_2 = new DialogueOption("What's a kitsune?", yuli9);

		DialogueOption oyuli1_2_1_1 = new DialogueOption("E-eat souls??", yuli5);
		DialogueOption oyuli1_2_1_2 = new DialogueOption("UM OK BYE", yuli6);

		DialoguePiece yuli10 = new DialoguePiece("Goodbye, traveler.");
		DialogueOption oyuli1_3 = new DialogueOption("Bye.", yuli10);

		yuli1.addOption(oyuli1_1);
		yuli1.addOption(oyuli1_2);
		yuli1.addOption(oyuli1_3);

		yuli2.addOption(oyuli1_1_1);
		yuli2.addOption(oyuli1_1_2);
		yuli4.addOption(oyuli1_1_2_1);
		yuli4.addOption(oyuli1_1_2_2);

		yuli7.addOption(oyuli1_2_1);
		yuli7.addOption(oyuli1_2_2);
		yuli8.addOption(oyuli1_2_1_1);
		yuli8.addOption(oyuli1_2_1_2);
		NPC yuli = new NPC("Yuli", "A fox kitsune woman with striking features and a mysterious aura.", yuli1);

		tavernInside.addNPC(yuli);

		
		
		
		
	}
	
	
	
	// endings 
	private boolean checkEnding() {
		boolean playGame = true;
		if (player.hasItem("C4")) {
			System.out.println("If A is for Apple and B is for Banana, then what is C for?");
			try {
	            // Pause execution for 500 milliseconds (0.5 seconds)
	            Thread.sleep(1200);
	        } catch (InterruptedException e) {
	            // Handle the exception if the sleep is interrupted
	            System.err.println("Sleep interrupted: " + e.getMessage());
	        }
			System.out.println("C4!");
			try {
	            // Pause execution for 500 milliseconds (0.5 seconds)
	            Thread.sleep(1200);
	        } catch (InterruptedException e) {
	            // Handle the exception if the sleep is interrupted
	            System.err.println("Sleep interrupted: " + e.getMessage());
	        }
	        System.out.println( ".......            ......          .......          .......          .......          ........     \n" +
			                     ".......             ....          .......   ..       ......         ........          .......       \n" +
			                     "....                            ........ ................. .....  .......               ...         \n" +
			                     "...          .....      ..      ........................................    ..                      \n" +
			                     "            ......      .................................................. .........                \n" +
			                     "         .......        ............................................................                \n" +
			                     "       ......... ..  ........................:::..:::-:::...:.......................        .....   \n" +
			                     "     ......... ... ...........:.:::::::-::--------:::-----::........................        .....   \n" +
			                     "    ........  ............:--==-=--==-=--+**--:=--===-----=-....:--:.................    ........   \n" +
			                     "    ......      ........:-++--------====+=-:----+**:::::::::::--::-----=-....:.................     \n" +
			                     "    ....  ..   .........--=+**+==-==-===-=++=--::--=========-=-=+++=-:--=-:..................       \n" +
			                     "             ..........-=+*#*###===+#**==+=:---====+**++**+*#*++==+*+==-++==--..............        \n" +
			                     "..       ...........:-=+*##%%%%%==+#%#====----:-=+##=++=+++++++=++=-=*=+==+++=:............         \n" +
			                     "..       .........:-=++###%@%*==+##%%+-----:-==++#*****%%%#*####**##+*#***====-.............     ...\n" +
			                     "          ........=*%#+*##%%%*=+#%#+-====--=-==*#**#%%#######%%%########**++*+==--:.........    ....\n" +
			                     "        .........=**%**%#%##%*+***=--=**++=+++***##%*#%##%%###%#########%%*#*#++*=-................\n" +
			                     "      .........-*+*%**#%#%%%*-=++=+++**#*++*##%%%##*#%%%%%%%##*#*#%%%%%%%%**#**++#+=................\n" +
			                     "    ..........-+***%%+##%%%%*++*++=+*###****#%%%###%%%%%%%%%#%%####%%%%%####*+#%#***+.............  \n" +
			                     "   ...........:*+***######%#*=+**++***##+#*#%%%%#%@@%%%%%%%##%%%####%%%%#*#####%#*#*+...........    \n" +
			                     " ..............=+*=+#**#%%%#*+*****##**##*###%%@%%%@%%%@%%%#%%*##%####%%##%%%%%#####-.:.......      \n" +
			                     "...... ........=**##%#%%##%%++*%#+*####-==*###%#*#%@%%%%#***%#*#**#%%%%%#**%@%%##%%*#*-........     \n" +
			                     " ...    .....:+****##**##%%%%***+*%%%%*=**+**=*##++###*++==##+*#####+*#####%@%#%@%#*%#-........ .   \n" +
			                     "       ......:+#**#%%**####****=+*##%%%%==*+*##%%+#%%@@%%###-:*%##%####+##***##%%%#%%*:..........   \n" +
			                     "     ........-+*#*%##*#%##%#+++*+**%%#%#*%#+##%%%%#%@@@#%%%#=#*%%########****####%%#-...........    \n" +
			                     "    ..:--.....-+####%#%%*%##++*++*+*####++***##%%##%@@%%%%#*%##%@%%#%#*##*#*+##**#%%*+...........   \n" +
			                     ".     .........:*##%%#%%**%*+*++#*++++*+=#****#*#**#%%@@%%%##**%%%##%#*##*##**##*#%#*-............. \n" +
			                     "..   ..........-####%%%#***#+*+#%#==--==-******+#***######*#****#####***###*#****##-..............  \n" +
			                     "   .............=###%@@%*+**#++**+*---==+:-+*#**#==+++#*#**##*++#*##**+*++****+*##*-............    \n" +
			                     "    .............*####%%@@*+##+=++=*+-==:-:::--:----===-=**++=+*%#*+**#*+++++*#***=............     \n" +
			                     "        ........::=#****%@%+==-:=+=-==-++:--:::-::-==----+====***+===++=**+++++*#=............  .   \n" +
			                     "..      ............:---=+***-::------::----:::::.:----=-::----::::=---=++=-+*=:..............      \n" +
			                     "... .... .................:...:::.........:.:.......-:--:--::::---::-::..::...................     .\n" +
			                     ".         ...................:::::::::::::..:::::.::-==--:::-----::::::........................  ...\n" +
			                     "      ......................:.:::::::::---:::-+++--=+-:--:----::::::........................... ....\n" +
			                     "      ...................:.:.::..:..::::--::--=**=====---=---:::..::......................... ......\n" +
			                     "      .....................::...::..:----::-=-=+=---===-----::.....:............................... \n" +
			                     "    ................................:::::::===+-==----==:-=-:::..................................   \n" +
			                     "   ......    .........................::.:::--==*+=+=-:---+*=:..................................    \n" +
			                     " .......       ....................:-::-=--==++*####*++*+==*+:::...................... ......       \n" +
			                     " ....          ....................:--=+==+####%#*%%###*#*=+===:...........................         \n" +
			                     "               ...................::=+++*+*#%###%###%%#*%%*##*=-:.....................              \n" +
			                     "            ........ .......::....---=+=*#%%#***#**##%%%%%##*#");

			
			playGame= false;
		} else if (player.hasItem("Sapphire Pendant") && player.getLocation().getName().equals("park")) {
			NPC jade = player.getLocation().getNPCByName("Jade");
			if (jade != null) {
				System.out.println("Good Ending:");
				System.out.println("You walk into the park, the gentle breeze swaying in your hair.");
				System.out.println("You approach the talk woman in the park with green hair, and look up into her eyes.");
				System.out.println("You hold out the sapphire pendant and her eyes widen slightly. Her eyes are as deep as the finest jade.");
				System.out.println("She smiles widely and takes the pendant, putting it on. It contrasts with her look, but in a good way.");
				System.out.println("\"I knew you would return to me\" she said. \"This proves it.\"");
				System.out.println("It turns out that the sapphire pendant had originally belonged to this woman, whose name was Jade, and she was your long lost sister. You recognized the sapphire pendant and so when you saw it at the jewelry stand, you had to purchase it and because of its lingering enchantment, you could find your way back to your family.");
				playGame = false;
			}
		} else if (player.hasItem("Square Rune") && player.getLocation().getName().equals("ruins")) {
			NPC purrok = player.getLocation().getNPCByName("Purrok");
			if (purrok != null) {
				System.out.println("Bad Ending: ");
				System.out.println("You step into the middle of the ruins, where circles of stone is etched into the ground and you stand in the middle of the clearing.");
				System.out.println("A hooded figure approaches you. He has a dark, menacing aura. You tremble slightly as he approaches you, but your feet are glued to the ground.");
				System.out.println("\"What do you want from me?\" you say, shakily.");
				System.out.println("The hooded figure looks you in the eyes. His eyes are a deep blood red and they shine with malice.");
				System.out.println("\"You have brought to me this sacred ruin.\" He said, taking your bag and pulling out the square rune. You are powerless to stop him or do anything.");
				System.out.println("\"With this, I can complete the ritual and further my master plans to take over the kingdom.\" He says.");
				System.out.println("\"W-what? How!\"");
				System.out.println("The man smiled, an awful grin.\" You don't need to concern yourself with that. You have already played your part. You shall be remembered as the great catalyst for this new era of time.\"");
				System.out.println("The figure approaches you and places a finger on your forehead. Red mist swirls around you, shaking the earth and blowing wind on your hair and clothes. You shut your eyes, and start to feel dizzy. You can hear the faintness of his voice as you slip out of consciousness. You hear the faint manical laughter as you lose consciousness.");
				System.out.println("Everything is dark.");
				playGame = false;
			}
		} else if (player.hasItem("Bird") && player.getLocation().getName().equals("forge")){
			NPC mason = player.getLocation().getNPCByName("Mason");
			if (mason != null) {
				System.out.println("??? ending: ");
				System.out.println("Mason turns around and sees you carrying a wiggling bird in your hands.");
				System.out.println("\"Traveler!\" he says, shocked. \"What are you doing with that bird?\"");
				System.out.println("You smile widely. \"Why, I'm saving it until it grows big and fat and then I'll eat it!\" you say cheerfully.");
				System.out.println("Mason's jaw drops in horror. He looks scandalized and fearful. \"WHAT?\" he shouts. \"WHY WOULD YOU DO THAT?\"");
				System.out.println("He tries to grab the bird out of your hands but you dodge him, keeping the bird secure firmly in your palms.");
				System.out.println("\"Don't do that!\" you scold. \"You'll scare away Big D.\"");
				System.out.println("Mason turns paler. \"Big D?! You named that bird Big D?????! How cruel are you to name it such and then say you will eat it!!\"");
				System.out.println("You laugh as Mason cries more, obviously it was a joke. But it was fun to play along.");
				System.out.println("After a while, you tell him such and he calms down. He seems upset at you for pulling such a mean prank, but slowly he starts to smile again. Together, you two take the bird and release it by the park where you found it. Happily ever after.");
				System.out.println(" Until the next bird comes, of course.");
				playGame = false;
			}
			
		}
		return playGame;
	}
	
	// takes a command and executes
	private boolean processCommand(String input, Player player) {
		boolean playGame = true;
		// find the command based on input
		Command command = CommandSelection.getCommand(input, player, dialogueSystem, this);
		
		if (command != null) {
			command.execute();
			playGame = checkEnding(); // Check for endings after every command
		} else {
			System.out.println("Well, are you going to do something?");
		}
		return playGame;
	}
	
	
	
	
	// MAIN METHOD TO PLAY THE GAME
	public void start() {
		Scanner scanner = new Scanner(System.in);
	
		// Sets player in the park initially 
		player.setLocation(worldMap.getLocation(10, 10));
		
		
		System.out.println("You wake up in a park, the grass beneath you green and lush, providing a comfortable bed. The sun shines warmly, casting a pleasant glow over you, while the sky above is a clear, bright blue. Birds chirp melodiously in the trees, adding a cheerful soundtrack to the serene morning. It is a perfect, peaceful day.");
			
		
		while (playGame) {
			
			
			
			// asks for input
			System.out.print("> ");
			String input = scanner.nextLine();
			
			// checks and runs command
			playGame = processCommand(input, player);
			
			
			
		
		}
		scanner.close();
		System.out.println("Thanks for playing!");
	}

}