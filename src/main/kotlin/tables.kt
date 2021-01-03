import org.jetbrains.exposed.sql.Table

object Pokemon: Table("pokemon") {
    val id = integer("id")
    val identifier = varchar("identifier", 79)
    val speciesId = integer("species_id") references PokemonSpecies.id

    override val primaryKey = PrimaryKey(id)
}

object PokemonStats: Table("pokemon_stats") {
    val pokemonId = integer("pokemon_id")
    val statId = integer("stat_id")

    override val primaryKey = PrimaryKey(pokemonId, statId)
}

object StatNames: Table("stat_names") {
    val statId = integer("stat_id")
    val localLanguageId = integer("local_language_id")
    val name = varchar("name", 79)

    override val primaryKey = PrimaryKey(statId, localLanguageId)
}

object PokemonSpecies: Table("pokemon_species") {
    val id = integer("id")
    val identifier = varchar("identifier", 79)

    override val primaryKey = PrimaryKey(id)
}

object PokemonSpeciesNames: Table("pokemon_species_names") {
    val pokemonSpeciesId = (integer("pokemon_species_id") references PokemonSpecies.id)
    val localLanguageId = integer("local_language_id")
    val name = varchar("name", 79)
    val genus = text("genus")

    override val primaryKey = PrimaryKey(pokemonSpeciesId, localLanguageId)
}

object PokemonSpeciesFlavorText: Table("pokemon_species_flavor_text") {
    val speciesId = integer("species_id") references PokemonSpecies.id
    val versionId = integer("version_id")
    val languageId = integer("language_id")
    val flavorText = text("flavor_text")

    override val primaryKey = PrimaryKey(speciesId, versionId, languageId)
}

object PokemonTypes: Table("pokemon_types") {
    val pokemonId = integer("pokemon_id") references Pokemon.id
    val typeId = integer("type_id") references Types.id
    val slot = integer("slot")

    override val primaryKey = PrimaryKey(pokemonId, slot)
}

object Types: Table("types") {
    val id = integer("id")
    val identifier = varchar("identifier", 79)

    override val primaryKey = PrimaryKey(id)
}

object TypeNames: Table("type_names") {
    val typeId = integer("type_id") references Types.id
    val localLanguageId = integer("local_language_id")
    val name = varchar("name", 79)

    override val primaryKey = PrimaryKey(typeId, localLanguageId)
}