abstract class Card(var letterIndex: String = ""){

    companion object {
        var cardList: MutableList<MutableList<Card>> = mutableListOf()
    }


    abstract class CardHolder{

        data object StoriesHolder{
            data object UsualStory: CardHolder() {
                var stories: MutableList<Story.UsualStory> = mutableListOf()
                fun addCardList(list: MutableList<Story.UsualStory>) {
                    stories += list
                }

                fun placeCardOnSheet(){
                    for (story in stories){
                        ExcelCard.ExcelStory.ExcelUsualStory(story)
                    }

                }
            }
            data object FixedDateStory: CardHolder() {
                var stories: MutableList<Story.FixedDateStory> = mutableListOf()
                fun addCardList(list: MutableList<Story.FixedDateStory>) {
                    stories += list
                }
            }
            data object OptimizationStory: CardHolder() {
                var stories: MutableList<Story.OptimizationStory> = mutableListOf()
                fun addCardList(list: MutableList<Story.OptimizationStory>) {
                    stories += list
                }
            }
            data object ExpediteStory: CardHolder() {
                var stories: MutableList<Story.ExpediteStory> = mutableListOf()
                fun addCardList(list: MutableList<Story.ExpediteStory>) {
                    stories += list
                }
            }
            }
        data object TroubleHolder: CardHolder(){
             var stories: MutableList<Trouble> = mutableListOf()
            fun addCardList(list: MutableList<Trouble>) {
                stories += list
            }}
        data object ModificationHolder: CardHolder(){
            var stories: MutableList<Modification> = mutableListOf()
            fun addCardList(list: MutableList<Modification>) {
                stories += list
            }
        }
        }

    abstract class Builder<B : Builder<B, C>, C:Card> {
        abstract val card: C
        abstract fun setLetterIndex(): B
        abstract fun self(): B
        abstract fun build(): C
        abstract class TextCardBuilder<B: TextCardBuilder<B,C>, C:TextCard>: Builder<B,C>() {
            abstract fun setText():B
            abstract class TroubleBuilder<B: TroubleBuilder<B,C>, C: Trouble>: TextCardBuilder<B, C>(){
                override fun setLetterIndex():B {
                    card.letterIndex = Data.LetterIndexMap.MAP["Trouble"] ?: ""
                    return self()
                }

                abstract fun setDangerScore():B
                class HarmlessTroubleBuilder(): TroubleBuilder<HarmlessTroubleBuilder,Trouble.HarmlessTrouble>() {
                    override val card: Trouble.HarmlessTrouble = Trouble.HarmlessTrouble()
                    override fun setText():HarmlessTroubleBuilder {
                        card.text = Data.HarmlessTroubleList.getNext()
                        return this
                    }

                    override fun setDangerScore():HarmlessTroubleBuilder {
                        card.dangerScore = Data.TroubleRange.Harmless.RANGE.random()
                        return this
                    }

                    override fun self(): HarmlessTroubleBuilder {
                        return this
                    }

                    override fun build(): Trouble.HarmlessTrouble{
                        return card
                    }
                }

                class EasyTroubleBuilder: TroubleBuilder<EasyTroubleBuilder,Trouble.EasyTrouble>() {
                    override val card: Trouble.EasyTrouble = Trouble.EasyTrouble()
                    override fun setText(): EasyTroubleBuilder{
                        card.text = Data.EasyTroubleList.getNext()
                        return this
                    }

                    override fun setDangerScore(): EasyTroubleBuilder {
                        card.dangerScore = Data.TroubleRange.Easy.RANGE.random()
                        return this
                    }
                    override fun self(): EasyTroubleBuilder {
                        return this
                    }

                    override fun build(): Trouble.EasyTrouble{
                        return card
                    }

                }

                class SeriousTroubleBuilder: TroubleBuilder<SeriousTroubleBuilder,Trouble.SeriousTrouble>() {
                    override val card: Trouble.SeriousTrouble = Trouble.SeriousTrouble()
                    override fun setText(): SeriousTroubleBuilder{
                        card.text = Data.SeriousTroubleList.getNext()
                        return this
                    }

                    override fun setDangerScore(): SeriousTroubleBuilder {
                        card.dangerScore = Data.TroubleRange.Serious.RANGE.random()
                        return this
                    }
                    override fun self(): SeriousTroubleBuilder {
                        return this
                    }

                    override fun build(): Trouble.SeriousTrouble{
                        return card
                    }

                }

                class AwfulTroubleBuilder: TroubleBuilder<AwfulTroubleBuilder,Trouble.AwfulTrouble>() {
                    override val card: Trouble.AwfulTrouble = Trouble.AwfulTrouble()
                    override fun setText(): AwfulTroubleBuilder{
                        card.text = Data.AwfulTroubleList.getNext()
                        return this
                    }

                    override fun setDangerScore(): AwfulTroubleBuilder {
                        card.dangerScore = Data.TroubleRange.Awful.RANGE.random()
                        return this
                    }
                    override fun self(): AwfulTroubleBuilder {
                        return this
                    }

                    override fun build(): Trouble.AwfulTrouble{
                        return card
                    }

                }
            }
            class ModificationBuilder: TextCardBuilder<ModificationBuilder, Modification>(){
                override val card: Modification = Modification()
                override fun setLetterIndex(): ModificationBuilder {
                    card.letterIndex = Data.LetterIndexMap.MAP["Modification"] ?: ""
                    return this
                }
                override fun setText(): ModificationBuilder{
                    card.text = Data.ModificationList.getNext()
                    return this
                }

                override fun self(): ModificationBuilder {
                    return this
                }

                override fun build(): Modification {
                    return card
                }
            }

        }
        abstract class StoryBuilder<B: StoryBuilder<B,C>, C: Story>:Builder<B, C>(){

            abstract fun setTitle(): B
            fun setPrice(): B {
                val mapping = Data.BaseComplexityRange.RANGE.zip(Data.PriceRange.RANGE).toMap()
                card.price = mapping[card.baseComplexity]?: 0
                return self()
            }

            fun setEstimate(): B {
                val estimate = Story.Estimate(card.baseComplexity)
                card.devEstimate = estimate.devEstimate
                card.analystEstimate = estimate.analystEstimate
                card.testEstimate = estimate.testEstimate
                return self()
            }

            class UsualStoryBuilder(i: Int): StoryBuilder<UsualStoryBuilder, Story.UsualStory>(){
                override val card: Story.UsualStory = Story.UsualStory(i)
                override fun self(): UsualStoryBuilder {
                    return this
                }

                override fun setLetterIndex(): UsualStoryBuilder {
                    card.letterIndex = Data.LetterIndexMap.MAP["UsualStory"] ?: ""
                    return this
                }

                override fun setTitle(): UsualStoryBuilder {
                    card.title = "Стандартная история"
                    return this
                }

                override fun build(): Story.UsualStory{
                    return card
                }


            }
            abstract class StoryWithDueDateBuilder<B: StoryWithDueDateBuilder<B,C>, C: Story.StoryWithDueDate>: StoryBuilder<B,C>(){
                abstract fun setDueDay(): B
                class FixedDateStoryBuilder(i: Int): StoryWithDueDateBuilder<FixedDateStoryBuilder, Story.FixedDateStory>(){
                    override val card: Story.FixedDateStory = Story.FixedDateStory(i)
                    override fun self(): FixedDateStoryBuilder {
                        return this
                    }

                    override fun setLetterIndex(): FixedDateStoryBuilder {
                        card.letterIndex = Data.LetterIndexMap.MAP["UsualStory"] ?: ""
                        return this
                    }

                    override fun setTitle() :FixedDateStoryBuilder {
                        card.title = "Крайний срок: ${card.dueDay} день"
                        return this
                    }

                    override fun setDueDay(): FixedDateStoryBuilder{
                        card.dueDay = Data.DueDayRange.getNext()
                        return this
                    }

                    override fun build(): Story.FixedDateStory{
                        return card
                    }


                }
                class ExpediteStoryBuilder(i: Int): StoryWithDueDateBuilder<ExpediteStoryBuilder, Story.ExpediteStory>(){
                    override val card: Story.ExpediteStory = Story.ExpediteStory(i)
                    override fun setLetterIndex(): ExpediteStoryBuilder {
                        card.letterIndex = Data.LetterIndexMap.MAP["ExpediteStory"] ?: ""
                        return this
                    }

                    override fun setDueDay():ExpediteStoryBuilder {
                        card.dueDay = (3..5).random()
                        return this
                    }
                    override fun setTitle(): ExpediteStoryBuilder {
                        card.title = "Срок: ${card.dueDay} дня"
                        return this
                    }

                    override fun self(): ExpediteStoryBuilder {
                        return this
                    }

                    override fun build(): Story.ExpediteStory{
                        return card
                    }


                }
            }
            abstract class StoryWithStabilityScoreBuilder<B: StoryWithStabilityScoreBuilder<B,C>, C: Story.StoryWithStabilityScore>:StoryBuilder<B,C>(){

                fun setStabilityScore(): B{
                    val mapping = Data.BaseComplexityRange.RANGE.zip(Data.StabilityScoreRange.RANGE).toMap()
                    card.stabilityScore = mapping[card.baseComplexity]?: 0
                    return self()
                }
                class OptimizationStoryBuilder(i: Int): StoryWithStabilityScoreBuilder<OptimizationStoryBuilder, Story.OptimizationStory>(){
                    override val card: Story.OptimizationStory = Story.OptimizationStory(i)

                    override fun setLetterIndex(): OptimizationStoryBuilder {
                        card.letterIndex = Data.LetterIndexMap.MAP["OptimizationStory"] ?: ""
                        return this
                    }
                    override fun setTitle(): OptimizationStoryBuilder {
                        card.title = Data.OptimizationList.getNext()
                        return this
                    }

                    override fun self(): OptimizationStoryBuilder {
                        return this
                    }

                    override fun build(): Story.OptimizationStory {
                        return card
                    }
                }
            }
        }

    }
    }

