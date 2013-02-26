copy .\dist\KindleChineseChess.jar KindleChineseChess.azw2
jarsigner -keystore "developer.keystore" -storepass password "KindleChineseChess.azw2" dktest
jarsigner -keystore "developer.keystore" -storepass password "KindleChineseChess.azw2" ditest
jarsigner -keystore "developer.keystore" -storepass password "KindleChineseChess.azw2" dntest
