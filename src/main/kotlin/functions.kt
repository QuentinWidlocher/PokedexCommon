fun getByName(name: String, database: Database? = null): PokedexEntry? {

    var result: PokedexEntry? = null

    transaction(database) {

        val rows =
            ( PokemonSpecies
                .innerJoin(PokemonSpeciesNames)
                .innerJoin(PokemonSpeciesFlavorText)
                .innerJoin(Pokemon)
                .innerJoin(PokemonTypes)
                .innerJoin(Types)
                .innerJoin(TypeNames)
                    )
                .slice(
                    Pokemon.identifier,
                    PokemonSpeciesNames.name,
                    PokemonSpeciesNames.genus,
                    PokemonSpeciesFlavorText.flavorText,
                    TypeNames.name,
                )
                .select {
                    PokemonSpeciesNames.name.match("%$name%")
                        .and(PokemonSpeciesNames.localLanguageId eq 5)
                        .and(PokemonSpeciesFlavorText.languageId eq 5)
                        .and(Pokemon.identifier eq Pokemon.identifier)
                        .and(TypeNames.localLanguageId eq 5)
                }
                .groupBy(TypeNames.typeId)
                .toList()

        if (rows.isEmpty()) {
            println("Ca existe comme pokémon \"$name\" ? Essaye autre chose...")
            return@transaction
        }

        result = PokedexEntry(rows)
    }

    return result
}