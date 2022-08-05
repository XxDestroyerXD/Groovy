package com.example.musicapp1;

public class Songcollection {

    public Song[] songs = new Song[4];
//song collection containing all the songs' info
    public Songcollection() {

        Song thewayyoulooktonight = new Song("S1001",
                "The Way You Look Tonight",
                "Michael Buble",
                "https://p.scdn.co/mp3-preview/b4893d249495bdad00828e6f0059b7e7d762da07?cid=2afe87a64b0042dabf51f37318616965\n",
                4.66,
                R.drawable.michael_buble_collection,
                "Some day when I'm awfully low " +
                        "When the world is cold I will feel a glow just thinking of you " +
                        "And the way you look tonight " +
                        "You're lovely with your smile so warm And your cheeks so soft " +
                        "There is nothing for me but to love you And the way you look tonight"
                        +"With each word your tenderness grows Tearing my fears apart " +
                        "And that laugh that wrinkles your nose " +
                        "Touches my foolish heart " +
                        "Yes you're lovely never ever change " +
                        "Keep that breathless charm " +
                        "Wont you please arrange it? " +
                        "Cause I love you Just the way you look tonight " +
                        "With each word your tenderness grows"
                        +"Tearing my fears apart " +
                        "And that laugh that wrinkles your nose " +
                        "Touches my foolish heart " +
                        "Yes you're lovely never ever change Keep that breathless charm " +
                        "Won't you please arrange it? " +
                        "Cause I love you Just the way you look tonight " +
                        "Just the way you look tonight " +
                        "Darling Just the way you look tonight",
                "https://i.scdn.co/image/ab67616d0000b2735f3f20826d44c30a017fd68e");
        Song BillieJean = new Song("S1002",
                "Billie Jean",
                "Michael Jackson",
                "https://p.scdn.co/mp3-preview/71638a1eac196a5daa9fbf152693585e323d8558?cid=2afe87a64b0042dabf51f37318616965\n",
                4.9,
                R.drawable.billie_jean,
                "She was more like a beauty queen from a movie scene I said don't mind, but what do you mean, I am the one Who will dance on the floor in the round? She said I am the one, who will dance on the floor in the round She told me her name was Billie Jean, as she caused a scene Then every head turned with eyes that dreamed of being the one Who will dance on the floor in the round People always told me be careful of what you do And don't go around breaking young girls' hearts And mother always told me be careful of who you love And be careful of what you do 'cause the lie becomes the truth Billie Jean is not my lover She's just a girl who claims that I am the one But the kid is not my son She says I am the one, but the kid is not my son For forty days and forty nights The law was on her side But who can stand when she's in demand Her schemes and plans 'Cause we danced on the floor in the round So take my strong advice, just remember to always think twice (Do think twice, do think twice) She told my baby we'd danced 'til three, then she looked at me Then showed a photo my baby cried his eyes were like mine (oh, no) 'Cause we danced on the floor in the round, baby People always told me be careful of what you do And don't go around breaking young girls' hearts She came and stood right by me Just the smell of sweet perfume This happened much too soon She called me to her room Billie Jean is not my lover She's just a girl who claims that I am the one But the kid is not my son Billie Jean is not my lover She's just a girl who claims that I am the one But the kid is not my son She says I am the one, but the kid is not my son She says I am the one, but the kid is not my son Billie Jean is not my lover She's just a girl who claims that I am the one But the kid is not my son She says I am the one, but the kid is not my son She says I am the one You know what you did, (she says he is my son) breaking my heart babe She says I am the one Billie Jean is not my lover Billie Jean is not my lover Billie Jean is not my lover Billie Jean is not my lover (don't Billie Jean) Billie Jean is not my lover Billie Jean is not my lover",
                "https://i.scdn.co/image/ab6761610000e5eba2a0b9e3448c1e702de9dc90");
        Song One = new Song("S1003",
                "One",
                "Ed Sheeran",
                "https://p.scdn.co/mp3-preview/ce8cbb9dfa110d2a2ec6c0b9a1f8d821a425b86b?cid=2afe87a64b0042dabf51f37318616965\n ",
                4.21,
                R.drawable.photograph,
                "test2",
                "lol");
        Song Glimpseofus = new Song("S1004",
                "Glimpse Of Us",
                "Joji",
                "https://p.scdn.co/mp3-preview/071c22f355ed0d03fdc176dcb25a487f5ffb661c?cid=2afe87a64b0042dabf51f37318616965\n",
                3.89,
                R.drawable.glimpse_of_us,
                "test3",
                "lol2");
        songs[0]= thewayyoulooktonight;
        songs[1]= BillieJean;
        songs[2]= One;
        songs[3]= Glimpseofus;


    }
//searches the song by using it's id
    public int searchSongById(String id ) {

        for (int i = 0; i < songs.length; i++) {
            Song tempSong = songs[i];
            String tempID = tempSong.getId()    ;
            if (tempID.equals(id)){
                return i;

            }


        }
      return 0;
    }
//uses the song index to retrieve the corresponding song info
    public Song getCurrentSongs(int index){
        return songs[index];
    }

//changes the song to the next song in the list
    public int getNextSong(int currentSongIndex){
        if(currentSongIndex>= 3){
            return currentSongIndex;
        }
        else{
            return currentSongIndex+1;
        }
    }

//changes the song to the previous song in the list
    public int getPrevSong(int currentSongIndex) {
        if (currentSongIndex <= 0) {
            return currentSongIndex;
        } else {
            return currentSongIndex - 1;
        }
    }
}
