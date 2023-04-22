package comm.MyPlaylist.source;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MyPlayist {

	private int maxPlayistCapacity;
	private HashMap<String, List<String>> userSongPlayist = new HashMap<String, List<String>>();

	public MyPlayist(int playistCapacity) {
		this.maxPlayistCapacity = playistCapacity;
	}

	public void addSongToPlayist(String userName, String songName) {
		List<String> playList;
		if (userSongPlayist.containsKey(userName)) {
			playList= userSongPlayist.get(userName);
			if (playList.size() < this.maxPlayistCapacity) {
				playList.add(songName);

			} else {
				playList.remove(0);
				playList.add(songName);
			}
		} else {
			playList = new ArrayList<>();
			playList.add(songName);
			userSongPlayist.put(userName, playList);
		}
	}

	public List<String> getPlayist(String userName) {
		return this.userSongPlayist.get(userName);
	}

	public String getLatestPlayedSong(String userName) {
		int ln = this.userSongPlayist.get(userName).size();
		return this.userSongPlayist.get(userName).get(ln - 1);
	}

	public void cleanUpPlayist() {
		userSongPlayist = new HashMap<String, List<String>>();
	}

}

