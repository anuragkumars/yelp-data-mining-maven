package wip;

public class test {

	public static void main1(String[] args) {
		// TODO Auto-generated method stub

		String elemInput11Text = "Showing 1-10 of 20";
		System.out.println(elemInput11Text.indexOf(" of "));
		String totalCount = elemInput11Text.substring(elemInput11Text.indexOf(" of ")+4);
		System.out.println("totalCount : (" + totalCount + ")");
		int totalResults = 0;
		try {
			totalResults = Integer.parseInt(totalCount);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String elemInput11Text = "Showing 1-10 of 20";
		System.out.println(elemInput11Text.indexOf("-"));
		String totalCount = elemInput11Text.substring(elemInput11Text.indexOf("-")+1);
		System.out.println("totalCount : (" + totalCount + ")");
		totalCount = totalCount.substring(0,totalCount.indexOf(" "));
		System.out.println();
		System.out.println("totalCount : (" + totalCount + ")");
		int totalResults = 0;
		try {
			totalResults = Integer.parseInt(totalCount);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
