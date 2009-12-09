package definebot;

import java.util.ArrayList;

public class SearchWord {

	ArrayList<String> DefineTable = new ArrayList<String>();

	public void searchUrls(String blipText, String word) {
		int i = 0;
		while (blipText.indexOf(word, i) != -1) {
			i = blipText.indexOf(word, i);

			int j = blipText.indexOf(" ", i + 1);
			if (j == -1) {
				j = blipText.length();
			}
			String wrd = blipText.substring(i + word.length(), j);

			if (wrd.length() > 0)
				DefineTable.add(wrd);
			i++;
		}
	}

	public ArrayList<String> getWords() {
		return DefineTable;
	}
}
