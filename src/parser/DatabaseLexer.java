// Generated from Database.g4 by ANTLR 4.5.3
package parser;

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class DatabaseLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.5.3", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		TABLEIDENT=1, COLUMNTYPE=2, NAME=3, WS=4;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"TABLEIDENT", "COLUMNTYPE", "NAME", "WS"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'Table'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "TABLEIDENT", "COLUMNTYPE", "NAME", "WS"
	};
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}


	public DatabaseLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "Database.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2\68\b\1\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\3\2\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3"+
		"\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\5\3%\n\3\3\4\3\4"+
		"\6\4)\n\4\r\4\16\4*\3\4\6\4.\n\4\r\4\16\4/\3\5\6\5\63\n\5\r\5\16\5\64"+
		"\3\5\3\5\2\2\6\3\3\5\4\7\5\t\6\3\2\6\3\2C\\\3\2c|\3\2\63;\5\2\13\f\17"+
		"\17\"\"=\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\3\13\3\2\2\2"+
		"\5$\3\2\2\2\7&\3\2\2\2\t\62\3\2\2\2\13\f\7V\2\2\f\r\7c\2\2\r\16\7d\2\2"+
		"\16\17\7n\2\2\17\20\7g\2\2\20\4\3\2\2\2\21\22\7U\2\2\22\23\7v\2\2\23\24"+
		"\7t\2\2\24\25\7k\2\2\25\26\7p\2\2\26%\7i\2\2\27\30\7K\2\2\30\31\7p\2\2"+
		"\31%\7v\2\2\32\33\7F\2\2\33\34\7q\2\2\34\35\7w\2\2\35\36\7d\2\2\36\37"+
		"\7n\2\2\37%\7g\2\2 !\7N\2\2!\"\7q\2\2\"#\7p\2\2#%\7i\2\2$\21\3\2\2\2$"+
		"\27\3\2\2\2$\32\3\2\2\2$ \3\2\2\2%\6\3\2\2\2&(\t\2\2\2\')\t\3\2\2(\'\3"+
		"\2\2\2)*\3\2\2\2*(\3\2\2\2*+\3\2\2\2+-\3\2\2\2,.\t\4\2\2-,\3\2\2\2./\3"+
		"\2\2\2/-\3\2\2\2/\60\3\2\2\2\60\b\3\2\2\2\61\63\t\5\2\2\62\61\3\2\2\2"+
		"\63\64\3\2\2\2\64\62\3\2\2\2\64\65\3\2\2\2\65\66\3\2\2\2\66\67\b\5\2\2"+
		"\67\n\3\2\2\2\7\2$*/\64\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}