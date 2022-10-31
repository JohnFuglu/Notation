package CoreStructure;

import Notation.Android.R;

public class SmileyUtil {

    public static Smileys smileyFromString(String s){
            switch (R.array.smileys){
                case 0:
                    return Smileys.TRESCONTENTplus;
                case 1:
                    return Smileys.TRESCONTENTmoins;
                case 2:
                    return Smileys.TRESCONTENT;
                case 3:
                    return Smileys.CONTENTplus;
                case 4:
                    return Smileys.CONTENTmoins;
                case 5:
                    return Smileys.CONTENT;
                case 6:
                    return Smileys.MOYENplus;
                case 7:
                    return Smileys.MOYENmoins;
                case 8:
                    return Smileys.MOYEN;
                case 9:
                    return Smileys.PASCONTENTplus;
                case 10:
                    return Smileys.PASCONTENTmoins;
                case 11:
                    return Smileys.PASCONTENT;
            }
            return null;
    }
}