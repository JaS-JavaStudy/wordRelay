static String lastWord = ""; // 이전 단어를 저장하는 변수

public boolean validCheck(String word) {
    if (lastWord.equals("")) { // 첫 번째 단어는 바로 통과
        lastWord = word;
        return true;
    }

    // 이전 단어의 마지막 글자와 받침을 분리
    char lastChar = lastWord.charAt(lastWord.length() - 1);
    char firstChar = word.charAt(0);

    // 받침이 있는 경우 받침을 무시하고 두음법칙 적용
    if (hasFinalConsonant(lastChar)) {
        lastChar = removeFinalConsonant(lastChar);
    }

    if ( applyInitialSoundRule(firstChar) == lastChar) {
        lastWord = word;
        return true;
    }

    // 단어 유효성 확인
    GetApi myword = new GetApi();
    boolean isValid = myword.isWordExist(word);

    if (isValid) {
        lastWord = word;
    }

    return isValid;
}

// 받침이 있는지 확인하는 메서드
public boolean hasFinalConsonant(char ch) {
    return (ch - '가') % 28 != 0;
}

// 받침을 제거한 음절을 반환하는 메서드
public char removeFinalConsonant(char ch) {
    int base = (ch - '가') / 28 * 28 + '가';
    return (char) base;
}

// 받침을 제거한 음절에 다시 받침을 넣는 메서드
public char appendFinalConsonant(char ch, int finalConsonantCode) {
    int base = (ch - '가') / 28 * 28 + '가'; // 받침 없는 기본 초성, 중성 조합
    return (char) (base + finalConsonantCode); // 기본 조합에 받침 코드 추가

// 두음법칙 적용 메서드
    public char applyInitialSoundRule (char firstChar) {
        switch (firstChar) {
            case '랴': return '야';
            case '려': return '여';
            case '료': return '요';
            case '류': return '유';
            case '리': return '이';
            case '례': return '예';
            case '라': return '나';
            case '래': return '내';
            case '로': return '노';
            case '루': return '누';
            case '르': return '느';
            case '뢰': return '뇌';
            case '녀': return '여';
            case '뇨': return '요';
            case '뉴': return '유';
            case '니': return '이';
            default: return firstChar;
        }
    }

