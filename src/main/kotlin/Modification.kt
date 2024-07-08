class Modification(): TextCard() {

    override fun setLetterIndex() {
        letterIndex = Data.LetterIndexMap.MAP["Modification"] ?: ""
    }
    override fun setText(){
        text = Data.ModificationList.getNext()
    }
}