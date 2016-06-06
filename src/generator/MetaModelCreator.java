package generator;

import generator.model.MetaModel;

/**
 * Interfejs tworzacy metaModel
 */
public interface MetaModelCreator {
	/**
	 * Tworzy metaModel
	 *
	 * @return MetaModel bazy danych stworzony na podstawie modelu
	 */
	MetaModel create ();

	
}
