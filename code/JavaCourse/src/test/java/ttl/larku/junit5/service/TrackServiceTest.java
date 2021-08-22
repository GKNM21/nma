package ttl.larku.junit5.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ttl.larku.domain.Track;
import ttl.larku.service.TrackService;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TrackServiceTest {

	private TrackService trackService;
	
	@BeforeEach
	public void setup() {
		trackService = new TrackService();
	}
	
	@Test
	public void testCreateTrack() {
		Track newTrack = trackService.createTrack("You Stepped Out Of A Dream", "Herb Ellis", "Three guitars in Bossa Nova Time",
				"04:19", "1963");
		
		Track result = trackService.getTrack(newTrack.getId());
		
		assertTrue(result.getTitle().contains(newTrack.getTitle()), () -> 
					"New Track has bad title: " + newTrack);
		int size = trackService.getAllTracks().size();
		assertEquals(1, size , () ->
					"All tracks should be 1 but is: " + size
		);
	}
	
	@Test
	public void testDeleteTrack() {
		Track track1 = new Track("The Shadow Of Your Smile", Arrays.asList("Big John Patton"),
				"Let 'em Roll", "06:15", "1965");
		track1 = trackService.createTrack(track1);
		Track track2 = new Track("I'll Remember April", Arrays.asList("Jim Hall", "Ron Carter"),
				"Alone Together", "05:54", "1972");
		track2 = trackService.createTrack(track2);
		
		assertEquals(2, trackService.getAllTracks().size());
		
		trackService.deleteTrack(track1.getId());
		
		assertEquals(1, trackService.getAllTracks().size());
		assertTrue(trackService.getAllTracks().get(0).getTitle().contains("April"));
	}

	@Test
	public void testDeleteNonExistentTrack() {
		Track track1 = new Track("The Shadow Of Your Smile", Arrays.asList("Big John Patton"),
				"Let 'em Roll", "06:15", "1965");
		track1 = trackService.createTrack(track1);
		Track track2 = new Track("I'll Remember April", Arrays.asList("Jim Hall","Ron Carter"),
				"Alone Together", "05:54", "1972");
		track2 = trackService.createTrack(track2);
		
		assertEquals(2, trackService.getAllTracks().size());
		
		trackService.deleteTrack(9999);
		
		assertEquals(2, trackService.getAllTracks().size());
	}
	
	@Test
	public void testUpdateTrack() {
		Track track1 = new Track("The Shadow Of Your Smile", Arrays.asList("Big John Patton"),
				"Let 'em Roll", "06:15", "1965");
		
		track1 = trackService.createTrack(track1);

		assertEquals(1, trackService.getAllTracks().size());
		
		track1.setTitle("A Shadowy Smile");
		trackService.updateTrack(track1);
		
		assertEquals(1, trackService.getAllTracks().size());
		assertTrue( trackService.getAllTracks().get(0).getTitle().contains("Shadowy"));
	}
}
