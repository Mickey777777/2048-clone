# 2048 Clone

Java Swing으로 구현한 2048 퍼즐 게임입니다.

## 게임 화면

<img width="562" height="670" alt="스크린샷 2025-11-22 16 46 31" src="https://github.com/user-attachments/assets/212c35c6-1585-4530-b4e5-f80f7f2b73bd" />

## 기능

- 4x4 그리드에서 타일 이동 및 병합
- 점수 시스템 (타일 병합 시 점수 획득)
- 최고 점수 저장 (로컬 파일)
- 게임 오버 화면 및 재시작 기능
- 2048 원작과 동일한 색상 테마

## 조작법

| 키 | 동작 |
|---|---|
| ↑ | 위로 이동 |
| ↓ | 아래로 이동 |
| ← | 왼쪽으로 이동 |
| → | 오른쪽으로 이동 |

게임 오버 시 **Play Again** 버튼을 클릭하여 재시작할 수 있습니다.

## 게임 규칙

1. 같은 숫자의 타일이 충돌하면 하나로 합쳐집니다
2. 합쳐진 타일의 값이 점수에 추가됩니다
3. 매 이동마다 빈 칸에 새 타일(2 또는 4)이 생성됩니다
4. 더 이상 이동할 수 없으면 게임 오버입니다

## 프로젝트 구조

```
src/
├── Main.java             # 프로그램 진입점
├── GameBoard.java        # 게임 로직 (이동, 병합, 점수)
├── GameConstants.java    # 상수 정의 (그리드 크기, 타일 크기 등)
├── GameStateChecker.java # 게임 상태 확인 (게임오버, 빈 타일 등)
├── BoardPanel.java       # 게임 보드 UI 렌더링
├── ScorePanel.java       # 점수 UI 렌더링
├── InputPanel.java       # 키보드/마우스 입력 처리
├── GameOverOverlay.java  # 게임 오버 화면 렌더링
├── ScoreManager.java     # 최고 점수 파일 저장/불러오기
└── Tile.java             # 타일 위치 정보
```

## 실행 방법

### 컴파일 및 실행

```bash
# 컴파일
javac src/*.java -d out

# 실행
java -cp out Main
```

### IDE에서 실행

`Main.java`의 `main` 메서드를 실행합니다.

## 기술 스택

- **언어:** Java 14+
- **GUI:** Java Swing
- **빌드:** javac

## 향후 개선 사항

- [ ] 타일 이동 애니메이션 추가
- [ ] 되돌리기 기능
- [ ] 사운드 효과
