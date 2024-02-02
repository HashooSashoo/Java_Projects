import java.util.Scanner;
/**
 * @author Zakariya Hashmi
 * @version 1.3
 */
public class MadLib extends GameParent
{
    /**
     * The prompt instance holds what the first word of each word request is (ex. Now, Lastly, First)
     * finalCall changes based on what madLib is being played. It holds the last word request call num to see what request can have the word "finally"
     * finalCallTracker increments everytime a word request is called to track when finalCall is being done
     * Finally, all other instance variables hold the number of each type of word request (ex. adjective, verb). This is to change the words in the prompt as well.
     */
    
    private String prompt;
    private int finalCall;
    private int finalCallTracker;
    private int properNounNum;
    private int otherNounNum;
    private int verbNum;
    private int adjectiveNum;
    private int adverbNum;
    private String[] madLibCollec = new String[2];
    
    
    public MadLib()
    {
        prompt = "";
        finalCall = 0;
        finalCallTracker = 0;
        properNounNum = 0;
        otherNounNum = 0;
        verbNum = 0;
        adjectiveNum = 0;
        adverbNum = 0;
        madLibCollec[0] = "Advertisement";
        madLibCollec[1] = "Help Please";
    }
    
    /**
     * local main method
     */
    public static void main(String[] args)
    {
        MadLib nut = new MadLib();
        nut.execute();
    }
    
    /**
     * the first Mad Lib<br>
     * it calls words 18 times (finalCall = times - 1 because the tracker starts at 0).
     */
    public String madLibOne()
    {
        finalCall = 17;
        String item = getPassiveNoun();
        return "It's very easy to " + getVerb() + " yourself when there is nothing to do.\nNot just yourself, but " + getPassiveNoun() + "s and " + getPassiveNoun() + "s as well." +
        "\nThat's why you need " + sayRecur(item) + " in your life. \nWith " + sayRecur(item) + " you will be so entertained that you will " + getVerb() + 
        " with joy so " + getAdverb() + " when you use this. \nYour " + sayRecur(item) + " will also have a " + getAdjective() + " and " + getAdjective() + 
        " " + getPassiveNoun() + " with industry stardard ability. \nAnd best of all, it's made in " + getProperNoun("country") + ". \nOne of the finest places to make your " +
        item + ". \nNo need to be " + getAdjective() + " and spend the entire day " + getVerb() + "ing your " + getProperNoun("part of the body") + " all day. " +
        " \nAnd the best part, it only costs " + getProperNoun("number") + " dollars! \nWhat a bargain! \nWith " + sayRecur(item) + ", you can get your way, all day, every day.";
    }
    
    /**
     * the second Mad Lib<br>
     * it calls words 41 times?
     */
    public String madLibTwo()
    {
        finalCall = 40;
        String hellVerb = getVerb();
        String fam = getProperNoun("family member (ex. uncle, mother, son)");
        String boyOrGirl = conditional("boy", "girl", "Tell me.", "he", "she");
        String hisOrHer = conditional("boy", "girl", "Tell me again, i forgor :(", "his", "her");
        String himOrHer = conditional("boy", "girl", "I remberd, then I forgor again. sorry :(", "him", "her");
        
        return "I can't take it anymore:\n\nEvery day, my " + getAdjective() + " " + sayRecur(fam) + " always ruins my day by " + sayRecur(hellVerb) + "ing in the house with " + sayRecur(hisOrHer) + " stupid " + getPassiveNoun() +
        ". \nI swear, " + sayRecur(boyOrGirl) + " " + sayRecur(hellVerb) + "s everyday so much that my " + getPassiveNoun() + " has gained sentience and " + getAdverb() + " asked that " + sayRecur(boyOrGirl) + " shut up.\nOne day, I heard " + 
        getAdjective() + " noises from our " + getProperNoun("room in your house") + ", so I " + getAdverb() + " went to check. \n" + sayRecur(boyOrGirl).toUpperCase()  + 
        " WAS " + sayRecur(hellVerb).toUpperCase() + "ING AGAIN!!! \nThis time with a " + getAdjective() + " " + getPassiveNoun() + " in one hand and a " + getPassiveNoun() + 
        " in the other. \nThe worst part? I caught " + sayRecur(hisOrHer) + " " + getProperNoun("job") + " FILMING " + sayRecur(himOrHer) + " " + sayRecur(hellVerb) + ".\nI just am in shock." +
        " We have known each other for " + getProperNoun("number") + " years. \nI " + getAdverb() + " brought " + sayRecur(himOrHer) + " to the " + getProperNoun("room in your house") + " to chat about the issue.\nMy " + sayRecur(fam) +
        " says that " + sayRecur(hellVerb) + "ing makes " + sayRecur(himOrHer) + " cool and " + getAdjective() + ", but it's doing the opposite. \nWhat do I do? Should I " + getVerb() + " " + sayRecur(himOrHer) + "? " + getVerb() + " " + sayRecur(himOrHer) + 
        "?\nPlease, I " + getAdverb() + " ask you for help." + "\n\nSincerely,\n" + getProperNoun("loved ones name");
    }
    
    /**
     * used if there is a recurring word in the Mad Lib<br>
     * this method prints the given word in memory while also adding the finalCallTracker
     */
    public String sayRecur(String word)
    {
        finalCallTracker++;
        return word;
    }
    
    
    /**
     * changes the prompt based on the conditions of the numWord (the word request num instances), or the finalCallTracker
     */
    public String firstWordPrompt(int numWord)
    {
        if (numWord == 0)
        {
            prompt = "";
        }
        
        else if (numWord == 1 || numWord == properNounNum)
        {
            prompt = "Tell me a ";
        }
        
        else if (finalCallTracker == finalCall)
        {
            prompt = "Finally, tell me one final ";
        }
        
        else
        {
            prompt = "Tell me another ";
        }
        return prompt;
    }
    
    /**
     * the scanner for every word request method<br>
     * it takes the word for the prompt and the word getter number
     */
    public String scanner(String wordType, int numWord)
    {
        Scanner urMom = new Scanner(System.in);
        System.out.println(firstWordPrompt(numWord) + wordType);
        String newWord = urMom.nextLine();
        return newWord;
    }
    
    /**
     * this method is used when you want a word that alternates with the MadLibber<br>
     * ex. gender, this or that, those kind of situations<br>
     * it takes the words to ask the MadLibber the prompt, and spits out a conditional word based on that prompt<br>
     * ex. "asks if boy or girl, spits out he or she"<br>
     * a comment exists if you want to put a quirky comment in the middle of the prompt ;)
     */
    public String conditional(String firstCon, String secondCon, String comment, String firstNewWord, String secondNewWord)
    {
        String respo = scanner("Is who/what you listed a " + firstCon + ", or a " + secondCon + " " + comment + "?", 0);
        
        if (respo.equals(firstCon))
        {
            finalCallTracker++;
            return firstNewWord;
        }
        
        else if (respo.equals(secondCon))
        {
            finalCallTracker++;
            return secondNewWord;
        }
        
        else
        {
            System.out.println("That's not allowed. I asked if it was a " + firstCon + ", or a " + secondCon + ".\nTry again.");
            conditional(firstCon, secondCon, comment, firstNewWord, secondNewWord);
        }
        return "";
    }
    
    /**
     * the verb word request method
     */
    public String getVerb()
    {
        verbNum++;
        finalCallTracker++;
        return scanner("verb", verbNum);
    }
    
    /**
     * the common noun word request method
     */
    public String getPassiveNoun()
    {
        otherNounNum++;
        finalCallTracker++;
        return scanner("noun", otherNounNum);
    }
    
    /**
     * the proper noun word request method<br>
     * takes parameter of specific thing (ex. country, place)
     */
    public String getProperNoun(String typeOfNoun)
    {
        properNounNum++;
        finalCallTracker++;
        return scanner(typeOfNoun, properNounNum);
    }
    
    /**
     * the adjective word request method
     */
    public String getAdjective()
    {
        adjectiveNum++;
        finalCallTracker++;
        return scanner("adjective", adjectiveNum);
    }
    
    /**
     * the adverb word request method
     */
    public String getAdverb()
    {
        adverbNum++;
        finalCallTracker++;
        return scanner("adverb", adverbNum);
    }
    
    /**
     * a method that uses if statement to get the mad lib based on the condition number<br>
     * will be used when execution happens
     */
    public void getMadLib(int numMadLib)
    {
        if (numMadLib == 1)
        {
            System.out.println(madLibOne());
        }
        
        else if (numMadLib == 2)
        {
            System.out.println(madLibTwo());
        }
    }
    
    /**
     * the whole mad lib game
     */
    public void execute()
    {
        System.out.println("Welcome! Here are the Mad Libs that you can try...");
        System.out.println();
        for (int i = 0; i < madLibCollec.length; i++)
        {
            System.out.println((i + 1) + ". " + madLibCollec[i]);
        }
        System.out.println();
        
        System.out.println("Which one do you want?");
        Scanner urMom = new Scanner(System.in);
        int resp = Integer.parseInt(urMom.nextLine());
        getMadLib(resp);
        playAgain("That was fun.");
    }
}
