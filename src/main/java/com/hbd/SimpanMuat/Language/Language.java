package com.hbd.SimpanMuat.Language;
import java.util.Map;

public interface Language {
    Object AddElement(Object current , String key,  Object value);
    Map<String, Object> parseElements(String scripture);
    Object getInitialFormat();
    String LanguageObjectToString(Object langObj);
    String getExtension();
}

/**
 * What the attributes to parse should be
 *
 * Note : Everything is stored as string, the type here only shows what it will be parsed to eventually
 *
 * gameState :
 * CURRENT_TURN -> int
 * SHOP_ITEMS -> ARRAY OF {NAMA_PRODUK -> int, JUMLAH_PRODUK -> int}
 *
 * playerState :
 * JUMLAH_GULDEN -> int
 * JUMLAH_DECK -> int
 * ACTIVE_DECK_CARDS -> ARRAY OF {LOKASI_KARTU -> String, NAMA_KARTU -> String}
 * LADANG_CARDS -> ARRAY OF {
 *                  LOKASI_KARTU -> String,
 *                  NAMA_KARTU -> String,
 *                  PROGRESS_PANEN -> int,
 *                  ITEM_AKTIF -> ARRAY OF String
 * }
 */