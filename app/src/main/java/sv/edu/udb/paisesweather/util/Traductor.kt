package sv.edu.udb.paisesweather.util

object Traductor {

    /**
     * Traduce el nombre de una región de inglés a español
     */
    fun traducirRegion(region: String): String {
        return when (region.lowercase()) {
            "africa" -> "África"
            "americas" -> "América"
            "asia" -> "Asia"
            "europe" -> "Europa"
            "oceania" -> "Oceanía"
            "antarctic" -> "Antártida"
            else -> region
        }
    }

    fun traducirSubregion(subregion: String): String {
        return when (subregion.lowercase()) {
            "northern africa" -> "África del Norte"
            "sub-saharan africa" -> "África Subsahariana"
            "eastern africa" -> "África Oriental"
            "middle africa" -> "África Central"
            "southern africa" -> "África del Sur"
            "western africa" -> "África Occidental"

            "northern america" -> "América del Norte"
            "caribbean" -> "Caribe"
            "central america" -> "América Central"
            "south america" -> "América del Sur"
            "latin america" -> "América Latina"

            "central asia" -> "Asia Central"
            "eastern asia" -> "Asia Oriental"
            "south-eastern asia" -> "Sudeste Asiático"
            "southern asia" -> "Asia del Sur"
            "western asia" -> "Asia Occidental"
            "middle east" -> "Medio Oriente"

            "eastern europe" -> "Europa Oriental"
            "northern europe" -> "Europa del Norte"
            "southern europe" -> "Europa del Sur"
            "western europe" -> "Europa Occidental"
            "central europe" -> "Europa Central"
            "balkans" -> "Balcanes"

            "australia and new zealand" -> "Australia y Nueva Zelanda"
            "melanesia" -> "Melanesia"
            "micronesia" -> "Micronesia"
            "polynesia" -> "Polinesia"

            else -> subregion
        }
    }

    fun traducirIdioma(idioma: String): String {
        return when (idioma.lowercase()) {
            "english" -> "Inglés"
            "spanish" -> "Español"
            "french" -> "Francés"
            "german" -> "Alemán"
            "italian" -> "Italiano"
            "portuguese" -> "Portugués"
            "russian" -> "Ruso"
            "chinese" -> "Chino"
            "japanese" -> "Japonés"
            "korean" -> "Coreano"
            "arabic" -> "Árabe"
            "hindi" -> "Hindi"
            "bengali" -> "Bengalí"
            "urdu" -> "Urdu"
            "persian" -> "Persa"
            "turkish" -> "Turco"
            "vietnamese" -> "Vietnamita"
            "thai" -> "Tailandés"
            "dutch" -> "Neerlandés"
            "greek" -> "Griego"
            "hebrew" -> "Hebreo"
            "swedish" -> "Sueco"
            "norwegian" -> "Noruego"
            "danish" -> "Danés"
            "finnish" -> "Finés"
            "polish" -> "Polaco"
            "czech" -> "Checo"
            "hungarian" -> "Húngaro"
            "romanian" -> "Rumano"
            "bulgarian" -> "Búlgaro"
            "serbian" -> "Serbio"
            "croatian" -> "Croata"
            "slovak" -> "Eslovaco"
            "slovenian" -> "Esloveno"
            "ukrainian" -> "Ucraniano"
            "belarusian" -> "Bielorruso"
            "lithuanian" -> "Lituano"
            "latvian" -> "Letón"
            "estonian" -> "Estonio"
            "georgian" -> "Georgiano"
            "armenian" -> "Armenio"
            "azerbaijani" -> "Azerí"
            "kazakh" -> "Kazajo"
            "uzbek" -> "Uzbeko"
            "turkmen" -> "Turcomano"
            "kyrgyz" -> "Kirguís"
            "tajik" -> "Tayiko"
            "mongolian" -> "Mongol"
            "swahili" -> "Suajili"
            "amharic" -> "Amárico"
            "yoruba" -> "Yoruba"
            "igbo" -> "Igbo"
            "hausa" -> "Hausa"
            "somali" -> "Somalí"
            "afrikaans" -> "Afrikáans"
            "zulu" -> "Zulú"
            "xhosa" -> "Xhosa"
            "malay" -> "Malayo"
            "indonesian" -> "Indonesio"
            "filipino" -> "Filipino"
            "burmese" -> "Birmano"
            "khmer" -> "Jemer"
            "lao" -> "Lao"
            "nepali" -> "Nepalí"
            "sinhala" -> "Cingalés"
            "tamil" -> "Tamil"
            "telugu" -> "Telugu"
            "marathi" -> "Maratí"
            "gujarati" -> "Guyaratí"
            "punjabi" -> "Panyabí"
            "malayalam" -> "Malayalam"
            "kannada" -> "Canarés"
            "odia" -> "Oriya"
            "sanskrit" -> "Sánscrito"
            else -> idioma
        }
    }

    fun traducirMoneda(moneda: String): String {
        return when (moneda.lowercase()) {
            "united states dollar" -> "Dólar Estadounidense"
            "euro" -> "Euro"
            "british pound" -> "Libra Esterlina"
            "japanese yen" -> "Yen Japonés"
            "canadian dollar" -> "Dólar Canadiense"
            "australian dollar" -> "Dólar Australiano"
            "swiss franc" -> "Franco Suizo"
            "chinese yuan" -> "Yuan Chino"
            "swedish krona" -> "Corona Sueca"
            "norwegian krone" -> "Corona Noruega"
            "danish krone" -> "Corona Danesa"
            "russian ruble" -> "Rublo Ruso"
            "indian rupee" -> "Rupia India"
            "brazilian real" -> "Real Brasileño"
            "mexican peso" -> "Peso Mexicano"
            "argentine peso" -> "Peso Argentino"
            "chilean peso" -> "Peso Chileno"
            "colombian peso" -> "Peso Colombiano"
            "peruvian sol" -> "Sol Peruano"
            "venezuelan bolívar" -> "Bolívar Venezolano"
            "uruguayan peso" -> "Peso Uruguayo"
            "paraguayan guaraní" -> "Guaraní Paraguayo"
            "bolivian boliviano" -> "Boliviano Boliviano"
            "costa rican colón" -> "Colón Costarricense"
            "dominican peso" -> "Peso Dominicano"
            "honduran lempira" -> "Lempira Hondureño"
            "nicaraguan córdoba" -> "Córdoba Nicaragüense"
            "panamanian balboa" -> "Balboa Panameño"
            "salvadoran colón" -> "Colón Salvadoreño"
            "guatemalan quetzal" -> "Quetzal Guatemalteco"
            "israeli new shekel" -> "Nuevo Séquel Israelí"
            "saudi riyal" -> "Riyal Saudí"
            "uae dirham" -> "Dírham de los EAU"
            "qatari riyal" -> "Riyal Catarí"
            "kuwaiti dinar" -> "Dinar Kuwaití"
            "bahraini dinar" -> "Dinar Bareiní"
            "omani rial" -> "Rial Omaní"
            "turkish lira" -> "Lira Turca"
            "egyptian pound" -> "Libra Egipcia"
            "south african rand" -> "Rand Sudafricano"
            "nigerian naira" -> "Naira Nigeriana"
            "ghanaian cedi" -> "Cedi Ganés"
            "kenyan shilling" -> "Chelín Keniano"
            "tanzanian shilling" -> "Chelín Tanzano"
            "ugandan shilling" -> "Chelín Ugandés"
            "ethiopian birr" -> "Birr Etíope"
            "moroccan dirham" -> "Dírham Marroquí"
            "algerian dinar" -> "Dinar Argelino"
            "tunisian dinar" -> "Dinar Tunecino"
            else -> moneda
        }
    }

    fun traducirCondicionClima(condicion: String): String {
        return when (condicion.lowercase()) {
            "sunny" -> "Soleado"
            "clear" -> "Despejado"
            "partly cloudy" -> "Parcialmente Nublado"
            "cloudy" -> "Nublado"
            "overcast" -> "Cubierto"
            "mist" -> "Neblina"
            "fog" -> "Niebla"
            "light rain" -> "Lluvia Ligera"
            "moderate rain" -> "Lluvia Moderada"
            "heavy rain" -> "Lluvia Intensa"
            "light snow" -> "Nieve Ligera"
            "moderate snow" -> "Nieve Moderada"
            "heavy snow" -> "Nieve Intensa"
            "sleet" -> "Aguanieve"
            "light shower" -> "Chubasco Ligero"
            "moderate shower" -> "Chubasco Moderado"
            "heavy shower" -> "Chubasco Intenso"
            "thunderstorm" -> "Tormenta"
            "thunder" -> "Truenos"
            "lightning" -> "Relámpagos"
            "hail" -> "Granizo"
            "drizzle" -> "Llovizna"
            "freezing rain" -> "Lluvia Helada"
            "blizzard" -> "Ventisca"
            "windy" -> "Ventoso"
            "humid" -> "Húmedo"
            "dry" -> "Seco"
            "hot" -> "Caluroso"
            "cold" -> "Frío"
            "warm" -> "Templado"
            "cool" -> "Fresco"
            else -> condicion
        }
    }

    /**
     * Traduce estados de países
     */
    fun traducirStatus(status: String): String {
        return when (status.lowercase()) {
            "officially-assigned" -> "Asignado oficialmente"
            "user-assigned" -> "Asignado por usuario"
            "reserved" -> "Reservado"
            "deleted" -> "Eliminado"
            "transitional" -> "Transicional"
            "indeterminately-reserved" -> "Reservado indefinidamente"
            "exceptionally-reserved" -> "Excepcionalmente reservado"
            else -> status
        }
    }

    /**
     * Traduce zonas horarias
     */
    fun traducirZonaHoraria(zona: String): String {
        return when (zona) {
            "UTC" -> "Tiempo Universal Coordinado"
            "GMT" -> "Hora del Meridiano de Greenwich"
            else -> zona
        }
    }


    fun traducir(texto: String, tipo: String = "auto"): String {
        return when (tipo) {
            "region" -> traducirRegion(texto)
            "subregion" -> traducirSubregion(texto)
            "idioma" -> traducirIdioma(texto)
            "moneda" -> traducirMoneda(texto)
            "clima" -> traducirCondicionClima(texto)
            else -> texto
        }
    }
}