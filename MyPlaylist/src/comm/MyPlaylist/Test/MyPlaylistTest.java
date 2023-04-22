package comm.MyPlaylist.Test;



import java.util.*;
import org.testng.Assert;
import org.testng.annotations.*;

import comm.MyPlaylist.source.MyPlayist;

public class MyPlaylistTest {
	private MyPlayist myPlaylist;
	private int maxPlayistCapacity = 3;

//Initializing the play list class
	@BeforeClass
	private void testSetUp() {
		myPlaylist = new MyPlayist(maxPlayistCapacity);
	}

//Cleaning up the play list
	@AfterMethod
	private void cleanUpPlayist() {
		myPlaylist.cleanUpPlayist();
	}

//Adding songs to the play list, songs should be added successfully 
	@Test
	public void addSongsPlayistSongsAddedSuccesfully() {

		myPlaylist.addSongToPlayist("U1", "S1");
		myPlaylist.addSongToPlayist("U1", "S2");
		myPlaylist.addSongToPlayist("U1", "S3");
		List<String> expectedValue = new ArrayList<String>();
		expectedValue.add("S1");
		expectedValue.add("S2");
		expectedValue.add("S3");
		List<String> actualValue = myPlaylist.getPlayist("U1");
		Assert.assertEquals(actualValue, expectedValue, "Mismatch in actual and expected value.");
	}

//Adding more songs to the play list beyond the defined capacity
//, only adds max capacity number of songs
	@Test
	public void addSongsPlayistOnlyAddsMaxCapacitySongs() {

		myPlaylist.addSongToPlayist("U1", "S1");
		myPlaylist.addSongToPlayist("U1", "S2");
		myPlaylist.addSongToPlayist("U1", "S3");
		myPlaylist.addSongToPlayist("U1", "S4");
		List<String> expectedValue = new ArrayList<String>();
		expectedValue.add("S2");
		expectedValue.add("S3");
		expectedValue.add("S4");
		List<String> actualValue = myPlaylist.getPlayist("U1");
		Assert.assertEquals(actualValue, expectedValue,"Mismatch in epected and actual value.");
		Assert.assertEquals(actualValue.size(), maxPlayistCapacity, "Not received expected value.");
	}

//Adding songs for multiple users
	@Test
	public void addSongsToPlayistMultiUser() {
		myPlaylist.addSongToPlayist("U1", "S1");
		myPlaylist.addSongToPlayist("U1", "S2");
		myPlaylist.addSongToPlayist("U2", "S1");
		myPlaylist.addSongToPlayist("U2", "S2");
		myPlaylist.addSongToPlayist("U2", "S3");
		List<String> expectedValueUser1 = new ArrayList<String>();
		expectedValueUser1.add("S1");
		expectedValueUser1.add("S2");
		List<String> expectedValueUser2 = new ArrayList<String>();
		expectedValueUser2.add("S1");
		expectedValueUser2.add("S2");
		expectedValueUser2.add("S3");
		List<String> actualValueUser1 = myPlaylist.getPlayist("U1");
		List<String> actualValueUser2 = myPlaylist.getPlayist("U2");
		Assert.assertEquals(actualValueUser1, expectedValueUser1, "Mismatch in actual and expected value.");
		Assert.assertEquals(actualValueUser2, expectedValueUser2, "Mismatch in actual and expected value.");
	}

//Getting the latest play list song
	@Test
	public void getLatestPlayedSong() {
		myPlaylist.addSongToPlayist("U1", "S1");
		myPlaylist.addSongToPlayist("U1", "S2");
		myPlaylist.addSongToPlayist("U1", "S3");
		myPlaylist.addSongToPlayist("U1", "S4");
		myPlaylist.addSongToPlayist("U2", "S1");
		myPlaylist.addSongToPlayist("U2", "S2");
		myPlaylist.addSongToPlayist("U2", "S3");
		myPlaylist.addSongToPlayist("U2", "S4");
		myPlaylist.addSongToPlayist("U2", "S5");
		String expectedLatestSongUser1 = "S4";
		String expectedLatestSongUser2 = "S5";
		String actualLatestSongUser1 = myPlaylist.getLatestPlayedSong("U1");
		String actualLatestSongUser2 = myPlaylist.getLatestPlayedSong("U2");
		Assert.assertEquals(actualLatestSongUser1, expectedLatestSongUser1, "Mismatch expected played song.");
		Assert.assertEquals(actualLatestSongUser2, expectedLatestSongUser2, "Mismatch expected played song.");
	}
}

