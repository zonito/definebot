package definebot;

import com.google.wave.api.ProfileServlet;

@SuppressWarnings("serial")
public class Profile extends ProfileServlet {

	@Override
	public String getRobotName() {
		return "DefineBot";
	}

	@Override
	public String getRobotAvatarUrl() {
		return "http://definebot.appspot.com/images/define.gif";
	}

	@Override
	public String getRobotProfilePageUrl() {
		return "http://www.voizle.com/";
	}

}
