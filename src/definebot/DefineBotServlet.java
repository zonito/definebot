package definebot;

import java.util.List;

import com.google.wave.api.AbstractRobotServlet;
import com.google.wave.api.Blip;
import com.google.wave.api.Event;
import com.google.wave.api.EventType;
import com.google.wave.api.RobotMessageBundle;
import com.google.wave.api.StyleType;
import com.google.wave.api.TextView;
import com.google.wave.api.Wavelet;

@SuppressWarnings("serial")
public class DefineBotServlet extends AbstractRobotServlet {

	SearchWord sw = new SearchWord();
	Wavelet wavelet;
	ReadXml rx = new ReadXml();

	@Override
	public void processEvents(RobotMessageBundle bundle) {
		wavelet = bundle.getWavelet();
		if (bundle.wasSelfAdded()) {
			Blip blip = wavelet.appendBlip();
			TextView textView = blip.getDocument();
			textView
					.appendMarkup("Hi, I am your wave online Dictionary. To know meaning of words, write 'define:YOURWORD'.");
		}

		for (Event e : bundle.getEvents()) {
			if (e.getType() == EventType.BLIP_SUBMITTED) {
				defineWords(e);
			}
		}
	}

	private void defineWords(Event e) {
		TextView cont = e.getBlip().getDocument();
		String blipText = cont.getText();
		sw.searchUrls(blipText, "define:");

		List<String> list = sw.getWords();
		for (String word : list) {
			Blip blip = e.getBlip().createChild();
			TextView textView = blip.getDocument();
			String response = rx.getMeaning(word);
			if (response == null) {
				response = "Sorry, Word Not found!";
			}

			textView.setStyle(StyleType.BOLD);
			textView.setStyle(StyleType.ITALIC);
			textView.appendMarkup(response);
		}
	}
}
