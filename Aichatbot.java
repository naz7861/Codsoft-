import java.net.UnknownServiceException;
import java.util.Scanner;
import java.util.regex.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
public class Aichatbot
{

    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        System.out.println("Aichatbot:Hello!!how can I help you??");
        while (true)
        {
            System.out.println("You: ");
            String user_input= scanner.nextLine();
            if (user_input.equalsIgnoreCase("close"))
            {
                System.out.println("Aichatbot:Goodbye!!!");
                break;
            }
            String response= Aichatbot(user_input);
            System.out.println("Aichatbot: "+response);
        }
    }

    private static String Aichatbot(String user_input)
    {
        if (Pattern.compile("\\bhello\\b",Pattern.CASE_INSENSITIVE).matcher(user_input).find())
        {
            return "Hello!!how can i help you?";
        } else if (Pattern.compile("\\bhow are you?\\b",Pattern.CASE_INSENSITIVE).matcher(user_input).find())
        {
            return "I'm doing great.What about you?";
        } else if (Pattern.compile("\\bgoodbye\\b",Pattern.CASE_INSENSITIVE).matcher(user_input).find())
        {
            return "Gooodbye!!!Have a great day.";
        } else if (Pattern.compile("\\bthank you\\b",Pattern.CASE_INSENSITIVE).matcher(user_input).find())
        {
            return "You're welcome!!!!";
        } else if (Pattern.compile("\\btell me what is your name\\b",Pattern.CASE_INSENSITIVE).matcher(user_input).find())
        {
            return "I'm an AI chatbot ,how can assist you";
        } else if (Pattern.compile("\\b(?:current\\s+)?(?:date|time)\\b",Pattern.CASE_INSENSITIVE).matcher(user_input).find())
        {
            return getCurrentime();
        } else if (Pattern.compile("\\b(?:what is the)? weather\\b",Pattern.CASE_INSENSITIVE).matcher(user_input).find())
        {
            return getWeatherResponse();
        }
        else
        {
            return "I'm sorry,I didnt understand that";
        }

    }
    public static String getCurrentime()
    {
        LocalDateTime currentTime=LocalDateTime.now();
        DateTimeFormatter formatter=DateTimeFormatter.ofPattern("yyyy-MM-ddHH:mm:ss");
        return "The current date and time is "+currentTime.format(formatter);
    }
    public static String getWeatherResponse()
    {
        return "The weather is currently fine.";
    }
}
