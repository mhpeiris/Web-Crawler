import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.lang.Thread;
import java.lang.InterruptedException;
import java.util.ArrayList;
import java.lang.StringBuilder;
import java.io.BufferedReader;
import java.util.zip.GZIPInputStream;
import java.util.HashMap;

public class WebStats
{
	public static String currentURL = "https://www.tutorialspoint.com/java/java_string_substring.htm";
	public static int tagCount = 0;
	public static String str = "<html><head></head><body><div>Hello, World</div></body></html>";
	
	public static void main(String[] args) throws IOException 
	{
		System.out.println("Hello, Welcome to Tag Counter!");
		str = getWebPageSource();
		//System.out.println(str);
		tagCheck();	
		System.out.println("Tag count is: " + tagCount);
	}
	
	public static String getWebPageSource() throws IOException
	{
		URL url = new URL(currentURL);
		URLConnection connection = url.openConnection();
		BufferedReader sourceInput = null;
		
		if(connection.getHeaderField("Content-Encoding") != null && connection.getHeaderField("Content-Encoding").equals("gzip"))
		{
			sourceInput = new BufferedReader(new InputStreamReader(new GZIPInputStream(connection.getInputStream())));
		}
		else 
		{
            sourceInput = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        }
		
		String input;
		StringBuilder inputBuild = new StringBuilder();
		while ((input = sourceInput.readLine()) != null)
		{
			inputBuild.append(input);
		}
        sourceInput.close();
		return inputBuild.toString();
	}	
	
	public static void tagCheck()
	{
		char tagChar1 = '<';
		char tagChar2 = '>';
		char tagChar3 = '/';
		
		String sub = "";
		
		for(int i=0; i < str.length(); i++)
		{
			if((str.charAt(i) == tagChar1) && (str.charAt(i+1) != tagChar3))
			{
				tagCounter();
				sub = str.substring(i);
				tagName(sub);
			}
		}
	}
	
	public static void tagCounter()
	{
		tagCount++;
	}
	
	public static void tagName(String sub)
	{
		char tagChar2 = '>';
		ArrayList<Character> tagList = new ArrayList<Character>();
		for(int i=0; i < sub.length(); i++)
		{
			if((sub.charAt(i) != tagChar2) && (sub.charAt(i) != ' '))
			{
				tagList.add(sub.charAt(i));
			}
			else break;
		}
		
		StringBuilder tagString = new StringBuilder(tagList.size());
		for(Character ch: tagList)
		{
			tagString.append(ch);
		}
		tagString.append(tagChar2);
		
		tagMapper(tagString.toString());
		System.out.println(tagString.toString());
	}
	
	public static void tagMapper(String tag)
	{
		HashMap tagMap = new HashMap();
		//tagMap.put(tag);
	}
}