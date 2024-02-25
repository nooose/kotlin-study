# Kotlin 학습
- Kotlin 1.9.21
- JDK 17

# Coroutine
## 루틴 / 코루틴 비교
| 루틴                      | 코루틴                        |
|-------------------------|----------------------------|
|시작되면 끝날 때까지 멈추지 않는다.    | 중단되었다가 재개될 수 있다.           |
|한 번 끝나면 루틴 내의 정보가 사라진다. | 중단되더라도 루틴 내의 정보가 사라지지 않는다. |


## 스레드 / 코루틴 비교
|                       | 스레드                     | 코루틴                    |
|-----------------------|---------------------------|---------------------------|
| **개념**                | 프로세스보다 작은 개념      | 스레드보다 작은 개념        |
|                       | 한 스레드는 오직 한 프로세스에만 포함되어 있다.  | 한 코루틴의 코드는 여러 스레드에서 실행될 수 있다.|
| **Context switching** | context switching 발생 시, stack 영역이 교체된다. | (한 스레드에서 실행하는 경우) context switching 발생 시 메모리 교체가 없다. |
| **양보**                | OS가 스레드를 강제로 멈추고 다른 스레드를 실행한다. | 코루틴 스스로가 다른 코루틴에게 양보(`yield()`)한다. |

## CoroutineScope
- 코루틴이 탄생할 수 있는 영역
## CoroutineContext
- 코루틴과 관련된 데이터를 보관
## 주요 Dispatcher 종류
### Default
- 가장 기본적인 디스패처, CPU 자원을 많이 쓸 때 권장
- 별다른 설정이 없으면 이 디스패처가 사용됨
### IO
- I/O 작업에 최적화된 디스패처
### Main
- 보통 UI 컴포넌트를 조작하기 위한 디스패처
- 안드로이드 같은 UI가 필요한 프로젝트에서 사용
