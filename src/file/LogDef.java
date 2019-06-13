package file;

public class LogDef {

	public static boolean isLLOC(String s) {
		String log = s.toLowerCase();
		String[] logSymbol = { ".log:", " println(java.lang.String)", ".logger:", ".timber:" };
		for (String i : logSymbol) {
			if (log.contains(i))
				return true;
		}
		return false;
	}
}
