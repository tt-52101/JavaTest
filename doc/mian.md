#### glibc

≈是[GNU](https://baike.baidu.com/item/GNU)发布的libc库，即c[运行库](https://baike.baidu.com/item/运行库/5587282)。glibc是[linux系统](https://baike.baidu.com/item/linux系统/1732935)中最底层的[api](https://baike.baidu.com/item/api/10154)，几乎其它任何运行库都会依赖于glibc。glibc除了封装[linux](https://baike.baidu.com/item/linux)操作系统所提供的[系统服务](https://baike.baidu.com/item/系统服务/11027121)外，它本身也提供了许多其它一些必要功能服务的实现。由于 glibc 囊括了几乎所有的 [UNIX](https://baike.baidu.com/item/UNIX) 通行的标准，可以想见其内容包罗万象。而就像其他的 UNIX 系统一样，其内含的档案群分散于系统的树状[目录结构](https://baike.baidu.com/item/目录结构/4348167)中，像一个支架一般撑起整个操作系统。在 GNU/Linux 系统中，其C函式库发展史点出了GNU/Linux 演进的几个重要里程碑，用 glibc 作为系统的C函式库，是GNU/Linux演进的一个重要里程碑。

glibc 内存分配器

tcmalloc 内存分配器

springboot 内存分配

![流程图](https://awps-assets.meituan.net/mit-x/blog-images-bundle-2019a/3612ea5b.jpg)

流程图

整个内存分配的流程如上图所示。MCC扫包的默认配置是扫描所有的JAR包。在扫描包的时候，Spring Boot不会主动去释放堆外内存，导致在扫描阶段，堆外内存占用量一直持续飙升。当发生GC的时候，Spring Boot依赖于finalize机制去释放了堆外内存；但是glibc为了性能考虑，并没有真正把内存归返到操作系统，而是留下来放入内存池了，导致应用层以为发生了“内存泄漏”。所以修改MCC的配置路径为特定的JAR包，问题解决。笔者在发表这篇文章时，发现Spring Boot的最新版本（2.0.5.RELEASE）已经做了修改，在ZipInflaterInputStream主动释放了堆外内存不再依赖GC；所以Spring Boot升级到最新版本，这个问题也可以得到解决。





# [工程师的十条精进原则](https://tech.meituan.com/2018/08/16/10-principles-for-engineers.html)

云鹏

**每个人都应该有自己的原则，当我们需要作出选择时，一定要坚持以原则为中心。**

## 原则一：Owner意识

**认真负责是工作的底线**

**积极主动是“Owner意识”更高一级的要求**

## 时间观念

**做事有计划，工作分主次。** **工作安排要有计划性。工作安排要分清楚主次。**

## 原则三：以终为始

是史蒂芬·柯维在《高效能人士的七个习惯》中提到的一个习惯。它是以所有事物都经过两次创造的原则（第一次为心智上的创造 想清楚目标，第二次为实际的创造）为基础的。直观的表达就是：**先想清楚目标，然后努力实现。要根据问题设定目标，再进行优化**。

**带着目标去学习**

在学习之前，我们一定要问自己，这次学习的目标是什么？是想把Redis的持久化原理搞清楚，还是把Redis的主从同步机制弄明白，亦或是想学习整个Redis Cluster的架构体系。如果我们能够带着问题与目标，再进行相关的资料搜集与学习，就会事半功倍。这种学习模式的效果会比碎片化阅读好很多。

## 原则四：闭环思维

一个人是否靠谱，就看他能否做到凡事有交代，件件有着落，事事有回音。这就是闭环思维的重要性。**它强调的是一种即时反馈闭环**如果别人给我们分配了一个任务，不管完成的结果如何，一定要在规定的时间内给出明确的反馈。

**真正的闭环，要求我们对工作中的事情都能够养成良好的思维习惯，沟通要有结论，通知要有反馈，To Do要有验收。**

**“闭环思维”还要求能够定期主动进行阶段性的反馈。** 



## 原则五：保持敬畏

**当我们进入到一个新的团队，请先暂时忘掉之前的习惯，要尽快学习团队既有的规范，并且让自己与团队保持一致 **包括了解编码规范、对上线流程不了解，对回滚操作不熟悉，对SRE线上变更过程不了解等等。除了这些显而易见的规范，还有一些约定俗成的规则。个人建议是：如果有事情拿不准，不妨多问问其他同事，不要凭自己的感觉做事情。**让规范与约定与时俱进，也是另一种形式的敬畏。**

## 原则六：事不过二

**所有的评审与问题讨论，不要超过两次，所有的评审最多两次。**通过这种方式，倒逼利益相关方尽可能地做好需求与方案设计。评审会议组织前，尝试与所有相关人员达成一致，询问对方的意见，并进行有针对性的讨论，这样能够大大提升评审会议的效率和质量。

**同样的错误不能犯第二次**孔子云：“不迁怒，不贰过” 对故障原因进行5Why分析，给出明确可执行的To Do List。在错误中反思与成长，才能让我们成为更优秀的人。

## 原则七：设计优先

Uncle Bob曾说过：“软件架构的目标，是为了让构建与维护系统的所需人力资源最小化。”

**架构设计，并不仅仅关系到系统的质量，还关乎团队的效能问题。**开发周期在3pd以上的项目必须有设计文档，开发周期在5pd以上的项目必须有设计评审

**无数事实证明，忽略了前期设计，往往会导致后续开发周期被大幅拉长，给项目带来了很大的Delay风险。而且最可怕的是，不当的设计会给项目带来巨大的后期维护成本，我们不得不腾出时间，专门进行项目的优化与重构。**

无论什么时候都要记住“设计优先”这一原则。磨刀不误砍柴工，前期良好的设计，会给项目开发以及后期维护带来极大的收益。**“设计优先”这一原则，要求写别人看得懂的设计** 设计的过程是一种智力上的创造。我们更希望它能成为个人与集体智慧的结晶。

我个人认为设计应该尽量使用比较合理的逻辑，进而把设计中的一些点组织起来。比如可以使用从抽象到具体，由总到分的结构来组织材料。在设计过程中，要以需求为出发点，通过合理的抽象把问题简化，讲清楚各个模块之间的关系，再详细分述模块的实现细节。做完设计之后，可以发给比较资深的RD或者PM审阅一下，根据他们的反馈再进行完善。好的设计，一定是逻辑清晰易懂、细节落地可执行的。

## 原则八：P/PC平衡 

“P/PC平衡”原则，即产出与产能平衡原则。**产出与产能必须平衡，才能达到真正的高效能**。伊索寓言中讲述了一个《生金蛋的鹅》的故事。产出好比“金蛋”，产能好比“会下金蛋的鹅”。“重蛋轻鹅”的人，最终可能连产蛋的资产都保不住；“重鹅轻蛋”的人，最终可能会被饿死。

从系统的角度看，每一个系统都是通过持续不断地叠加功能来实现其产出，而系统的产能是通过系统架构的可扩展性、稳定性等一系列特性来表征。为了达到产出与产能的平衡，需要在不断支持业务需求的过程中，持续进行技术架构层面的优化。如果一味地做业务需求，经过一定的时间，系统会越来越慢，最终影响业务的稳定性；反之，一个没有任何业务产出的系统，最终会消亡。

RD通过做需求来给公司创造价值，实现自己的产出。而RD的产能是指技术能力、软素质、身体健康状况，有这些资本后，我们才能进行持续的产出。在日常工作中，我发现很多RD往往只重视产出。他们也在很努力地做项目，但是每一个项目所使用的方法，还是沿用自己先前一贯的思路。最终，不仅项目做得一般，还会抱怨自己得不到任何成长。这就是P/PC不平衡的体现。**如果能在做项目的过程中，通过学习总结持续提升自己的技术能力和软素质，并将其应用于项目实施交付中，相信一定会取得双赢的结果。**

“P/PC平衡”原则还适用于很多其他的领域，例如团队、家庭等，我本人也非常推崇这一原则。希望大家也能将其作为自身的一项基本原则，努力寻找到产出与产能的平衡点。

这个部分是我刚开始最读不懂的地方，最终也是我自己认为收获最大的地方。这点就道出了我当前的迷茫现状。在一个中小型公司，日复一日的做业务，公司发展前景不明朗。自己的提升也是很有限。所以日常的迷茫和惶恐。担心被业界淘汰，担心落入下乘。但是又死死的绑在无止境的业务变更上。其实并不是没有提升，只是自己没有总结

## 原则九：善于提问

**“善于提问”，首先要勤于提问**。求知欲源于好奇心，是人类的一种本能。波克定理告诉我们，**只有在争辩中，才可能诞生最好的主意和最好的决定**。很多同学评审全程一言不发，这就是浪费大家的时间。如果全程“打酱油”，那就失去了评审的意义。

**“善于提问”，还要懂得如何提问**。有的同学却提不出任何问题？除了知识储备、专业技能、经验等方面的差异外，还有一点很重要：**批判性思维**。图书如《批判性思维》、《学会提问》

## 原则十：空杯心态

“满招损，谦受益”，“空杯心态”是最后一项原则。往往表现为工作中把别人的建议当成是批评，不接受任何反对意见，学习上也缺乏求知的动力，其实每个人多少都会有一些自满，可怕的是不知道甚至不愿承认自满。

**保持“空杯心态”这一原则要求我们时刻进行自我检视与反省**。在某些方面，我们可能确实比其他人想得深入，但是这不代表在所有方面都能考虑周全。对于别人的建议，建议使用“善于提问”原则里提到的批判性思维仔细分析一下，虚心地吸取那些好的建议。

工作学习就像“练级打怪”，技能储备的越多，就越容易走到最后。