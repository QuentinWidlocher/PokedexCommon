import org.jetbrains.exposed.sql.ResultRow

data class PokedexEntry(
    val name: String,
    val genus: String,
    val description: String,
    val types: List<String>
) {
    constructor(rows: List<ResultRow>) : this(
        name = rows[0][PokemonSpeciesNames.name],
        genus = rows[0][PokemonSpeciesNames.genus],
        description = rows[0][PokemonSpeciesFlavorText.flavorText],
        types = rows.map { it[TypeNames.name] }
    )
}