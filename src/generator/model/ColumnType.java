package generator.model;

/**
 * Enum opisujacy typy kolumn.
 */
public enum ColumnType {

	/** The string. */
	STRING {
		@Override
		public String getJavaType() {
			return "String";
		}
	},

	/** The int. */
	INT {
		@Override
		public String getJavaType() {
			return "Int";
		}
	},

	/** The double. */
	DOUBLE {
		@Override
		public String getJavaType() {
			return "Double";
		}
	},

	/** The long. */
	LONG {
		@Override
		public String getJavaType() {
			return "Long";
		}
	};

	/**
	 * Zwraca klase typu w javie
	 * 
	 * @return Typ w javie
	 */
	public abstract String getJavaType();

	/**
	 * Metody lookup sluzaca do pobierania typu.
	 * 
	 * @param id
	 *            jest to nazwa typu
	 * @return zwraca typ kolumny, w razie niepowodzenia wyrzuca
	 *         RunTimeException
	 */
	public static ColumnType lookup(String id) {
		try {
			return ColumnType.valueOf(id.toUpperCase());
		} catch (IllegalArgumentException e) {
			throw new RuntimeException("Invaild type " + id);
		}
	}
}
