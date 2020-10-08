package EventList;

public class EventList {
	private String[] eventList;
	
	public EventList(String[] eventList) {
		this.eventList = new String[eventList.length];
		for(int i = 0; i< eventList.length; i++) {
			this.eventList[i] = eventList[i];
		}
	}
	
	public boolean isEventThere(String event) {
		for(int i = 0; i < this.eventList.length; i++) {
			if(event.equals(this.eventList[i]))
				return true;
		}
		return false;
	}
}
