package com.company;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.*;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.*;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.lang.Integer.*;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toSet;

public class Problems {


    public Problems() throws IOException {
    }

    //todo:pb 58
    public static LocalDateTime transform(String s) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy,HH:mm:ss");
        LocalDateTime ldt = LocalDateTime.parse(s, dtf);
        return ldt;
    }

    //todo:pb 59

    //cu un datetimeformatter imi aleg structura pe care vreau sa o aiba data mea: dee ex prima oara orele, lunile, minutele etc
    //dupa ce am ales formatul(dintre cele deja existente) creez un LocalDateTime , iar cu functia statica a clasei,
    //parsez string-ul dat de mine, cu formatul dat de mine, in LocalDateTime

    //todo: pb 60

    public static LocalDateTime dataActuala() {
        return LocalDateTime.now();
    }

    //todo: pb 61

    public static LocalDateTime dataSiTimpulActuale() {
        LocalDate ld = LocalDate.now();
        LocalTime lt = LocalTime.MIDNIGHT;
        return LocalDateTime.of(ld, lt);
    }

    //todo:pb 62

    public static Instant instant1() {
        //instant este o data la care putem calcula gen aceeasi data plus 2 ore, minus 2 ore
        //o data asupra careia se pot efectua calcule. si are o precizie mai buna decat Date si LocalDate
        Instant inst1 = Instant.now();
        return inst1.plus(4, ChronoUnit.HOURS);
    }

    //todo: pb 63
    //period se refera la o diferenta de timp exprimata in ani,luni,saptamani,zile
    //duration se refera la o diferenta in ore,minute,secunde

    public static Duration duration1(LocalDateTime ld1, LocalDateTime ld2) {
        return Duration.between(ld1, ld2);
    }

    public static Period period1(LocalDate ld1, LocalDate ld2) {
        return Period.between(ld1, ld2);
    }

    //todo:pb 64
    public static int extractYear(LocalDateTime ld1) {
        return ld1.getYear();
    }

    //todo:pb65
    public static LocalDateTime extractHour(LocalDateTime ld1) {
        return ld1.minusHours(2);
    }

    //todo:pb66
    //de facut

    //todo:pb67
    public static void displayTimeZonesIds() {
        Set<String> zoneIDs = ZoneId.getAvailableZoneIds();
        zoneIDs.forEach((zoneId) -> {
            System.out.println(zoneId);
        });
    }

    //todo:pb 68
    //de facut


    //todo:pb 69
    public static void convertUnix() {
        long unix = 3600000;
        Instant instant = Instant.ofEpochSecond(unix);
        LocalDateTime date = LocalDateTime.ofInstant(instant, ZoneId.of("America/Belize"));
        System.out.println(date);
    }


    //todo:pb70
    public static void firstDayInMonth(LocalDateTime ldt) {
        System.out.println(ldt.with(TemporalAdjusters.firstDayOfMonth()));
        System.out.println(ldt.with(TemporalAdjusters.lastDayOfMonth()));
    }

    //todo:pb71
    public static void offset(LocalDateTime ldt) {
        ZoneId zi = ZoneId.of("Europe/Berlin");
        ZoneOffset zo = zi.getRules().getOffset(ldt);
        System.out.println(zo);

    }

    //todo:pb72
    public static void convertDate(Date date) {
        System.out.println(date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        System.out.println(date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
        System.out.println(date.toInstant());
    }

    //todo:pb73
    public static void iterateRangeDates(LocalDate date1, LocalDate date2) {
        for (LocalDate ld = date1; date1.isBefore(date2); ld = ld.plusDays(1))
            System.out.println(ld);
    }

    //todo:pb74
    public static void age(LocalDate date1, LocalDate date2) {
        Period per = Period.between(date1, date2);
        System.out.println("age is: " + per.getYears() + per.getMonths() + per.getDays());
    }

    //todo:pb75
    public static void startFinalDay(LocalDate ldt, LocalTime lt) {
        System.out.println(LocalDateTime.of(ldt, lt));
        System.out.println(ldt.atTime(LocalTime.from(LocalDateTime.MAX)));
    }

    //todo:pb76
    public static void differenceBetween(LocalDate ld1, LocalDate ld2) {
        System.out.println(ld1.until(ld2));
    }

    //todo:pb77
    //de facut


    //todo:pb78
    public static void afisareVar() {
        var list = new ArrayList<String>();
        list.add("azorica");
        System.out.println(list);

    }

    //todo:pb79
    public static void varInteger() {
        var x = 10;
        System.out.println(x);
    }

    //todo:pb80

    public static String returneazaCatel() {
        return "catel";
    }

    public static void castVar() {
        var x = returneazaCatel();
        System.out.println(x);
    }

    //todo:pb81
    public static void avoidVar() {
        var x = (byte) 2; //pentru a evita ca x sa devina o variabila de tip int
    }

    //todo:pb82
    public static void avoidVar2() {
        var error = new Error();
        System.out.println(error); //m-am gandit ca ar fi destul de ambiguu daca am pune var deoarece nu am stii ce fel de eroare avem
    }


    //todo:pb83
//    public static void lvtiInterface(){
//        var x= new Instrumentation() {
//            @Override
//            public void redefineClasses(ClassDefinition... definitions) throws ClassNotFoundException, UnmodifiableClassException {
//
//            }
//        };
//    }


    //todo:pb84
    public static void lvtiDiamondOperator() {
        var list = new ArrayList<Double>();
        System.out.println(list);
    }

    //todo:pb85
    public static void arrayVar() {
        var arr = new char[100];
        arr[7] = 'c';
        System.out.println(arr);
    }

    //todo:pb86
    public static void lvtiCompound() {
        //var v1=9,v2=12; nu merge
        var v4 = 67;
        var v5 = 34;
        System.out.println(v4);
    }

    //todo:pb87
    public static void lvtiScope() {
        var collection = new Stack<String>();
        collection.add("ionut");
        collection.add("mircea");
        collection.add("azorel");
        //parcurgerea va fi ionut, mircea, azorel
        var collection2 = new ArrayDeque<String>();
        collection.add("ionut");
        collection.add("mircea");
        collection.add("azorel");
        //parcurgerea va fi azorel, mircea, ionut
    }

    //todo:pb88
    public static void lvtiTernary() {
        var x = new Object();
        x = (x instanceof String) ? 123 : "123";
    }

    //todo:pb89
    public static void lvtiFor() {
        ArrayList<String> lista = new ArrayList<>();
        lista.add("sdadas");
        lista.add("mihai");
        for (var i : lista) {
            System.out.println(i);
        }
    }

    //todo:pb90
    public static void lvtiStreams() {
        var str = Stream.of("azor", "azorica", "azorel");
        str.filter(x -> x.length() > 4).forEach(System.out::println);
    }

    //todo:pb91
    public static void lvtiLargeChains() {
        var s = "catelul merge la mare";
        var arr = s.split(" ");
        var sortedArr = Arrays.stream(arr).sorted();

    }

    //todo:pb92
//    public static var lvtiReturn(){
//        return "catel";
//    }

//    public static void afiseaza(var caine){
//        System.out.println(caine);
//    }

    public static void afiseaza() {
        var x = "caine";
        System.out.println(x);
    }

    //todo:pb93
    public static void lvtiAnonClasses() {
        var im = new InterfataMEa() {
            @Override
            public String returneazaCaine() {
                return "caine";
            }
        };

        var im2 = new InterfataMEa() {

            @Override
            public String returneazaCaine() {
                return "asddasdsda";
            }
        };
    }

    //todo:pb94
    public double returnSomeFinalDouble() {
        final double x = 9.6;
        return x; //this is final

    }

    public void finalEffectivelyFinal() {
        double y = this.returnSomeFinalDouble();
        System.out.println(y); //this is effectively final

    }

    //todo:pb95

    public static void lvtiLambdas() {
        var x = (Predicate<String>) s -> s.isEmpty();
    }

    //todo:pb96
    public static void lvtiNull() {
        //var x=null; //nu se poate initializa cu null
        //var y; nu se poate
        //private var x; nu se poate
    }

    //todo:pb97
    public static <T extends Number> void lvtiGenericTypesAdd(T t) {
        var list = new ArrayList<T>();
        list.add(t);
        list.add((T) Integer.valueOf(6));
        list.add((T) Double.valueOf(4.7));
        //list.add("4grerf"); nu se mai poate adauga string
    }

    //todo:pb98
    public static void lvtiWildCards() {
        var list = new ArrayList<>();
        list.add(6);
        list.add("dfds"); //putem adauga orice
        list.add(new Scanner(System.in));
        var x = Integer.class;
        var y = Scanner.class;
    }

    //todo:pb 99
    // la pb107 avem bubbleSort
    public static void selectionSort(int arr[]) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            int min_idx = i;
            for (int j = i + 1; j < n; j++)
                if (arr[j] < arr[min_idx])
                    min_idx = j;

            int temp = arr[min_idx];
            arr[min_idx] = arr[i];
            arr[i] = temp;
        }
    }

    //ne putem folosi si de Collections.sort()

    public static void shuffle1(ArrayList<String> a) {
        Collections.shuffle(a);
    }


    //todo:pb100
    public static Object findObjID(Object o, ArrayList<Object> objs) {
        for (int i = 0; i < objs.size(); i++)
            if (objs.get(i).equals(o))
                return objs.get(i);
        return null;
    }

    public static Object findObjValue(int id, ArrayList<Object> objs) {
        return objs.get(id);
    }

    //todo:pb101
    public static boolean arraysEquals(Object[] ar1, Object[] ar2) {
        if (ar1.length != ar2.length)
            return false;
        else {
            int cnt = 0;
            for (int i = 0; i < ar1.length; i++)
                if (ar1[i].equals(ar2[i]))
                    cnt++;

            if (cnt == ar1.length)
                return true;
            else
                return false;
        }
    }

    //todo:pb102
    public static int lexiArrays(Integer[] ar1, Integer[] ar2) {
        for (int i = 0; i < ar1.length; i++) {
            if (ar1[i] < ar2[i])
                return -1;
            if (ar1[i] > ar2[i])
                return 1;
        }

        return 0;
    }

    //todo:pb103
    public static Stream<String> streamFromArray(String[] arr) {
        Stream str = Stream.of(arr);
        return str;
    }

    //todo:pb104
    public static int getMax(Integer[] arr) {
        int max = MIN_VALUE;
        for (int i = 0; i < arr.length; i++)
            if (arr[i] > max)
                max = arr[i];

        return max;
    }


    public static int getMin(Integer[] arr) {
        int min = MAX_VALUE;
        for (int i = 0; i < arr.length; i++)
            if (arr[i] > min)
                min = arr[i];

        return min;
    }

    public static int average(Integer[] arr) {
        int sum = 0;
        for (int i = 0; i < arr.length; i++)
            sum = sum + arr[i];

        return sum / arr.length;
    }


    //todo:pb105

    public static void reverseArray(Integer[] arr) {
        int aux = 0;
        for (int i = 0; i < arr.length / 2; i++) {
            aux = arr[i];
            arr[i] = arr[(arr.length - 1) - i];
            arr[(arr.length - 1) - i] = aux;
        }
    }

    //todo:pb106
    public static void fill1(Integer[] list) {
        for (int i = 0; i < list.length; i++) {
            list[i] = 3;
        }
    }

    public static void fill2(Integer[] list) {
        Arrays.fill(list, 9);
    }

    public static void fill3(Integer[] list) {
        for (int i = 0; i < list.length; i++)
            list[i] = list[i] + 89;
    }

    public static void fill4(Integer[] list) {
        Arrays.parallelSetAll(list, t -> {
            if (list[t] % 2 == 1)
                return list[t] + 1;

            else
                return list[t];

        });
    }


    //todo:pb107
    public static void bubbleSort(Integer[] arr) {
        int n = arr.length;
        int temp = 0;
        for (int i = 0; i < n - 1; i++)
            for (int j = 0; j < n - 1 - i; j++)
                if (arr[j] > arr[j + 1]) {
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
    }

    public static int NGE(Integer[] arr) {
        bubbleSort(arr);
        return arr[arr.length - 2];
    }

    //todo:pb108
    public static void incrementLength(String[] a1, String[] a2, int newLength) {
        for (int i = 0; i < a1.length; i++)
            a1 = Arrays.copyOf(a1, i + newLength);
    }

    //todo:pb109
    public static void unmodifiableCollections() {
        List<String> list1 = List.of("dsa", "dsdsaasd", "75634");
        Set<Integer> set1 = Set.of(1233, 5671, 1);
        Map<String, Integer> map1 = Map.of("dasdsa", 6, "563", 653);
    }

    //todo:pb110
    public static Integer mappingValue(Map<String, Integer> map1) {
        map1.put("azor", 56);
        return map1.get("azor");
    }

    //todo:pb111
    public static String mapComputeAbsent(Map<Integer, String> map) {
        map.computeIfAbsent(20, k -> "Catel");
        map.computeIfPresent(40, (k, v) -> "sdadsa");
        return map.get(20);
    }

    //todo:pb112
    public static void removeFromMap(Map<String, Integer> map, int k) {
        map.remove(k);
    }

    //todo:pb113
    public static void replaceFromMap(Map<String, Integer> map, String k) {
        map.replace(k, 123);
    }

    //todo:pb114
    public static boolean compareMaps(Map<String, Integer> map1, Map<String, Integer> map2) {
        return map1.equals(map2);
    }


    //todo:pb116
    public static Map<String, Integer> copyMap(Map<String, Integer> map) {
        Map<String, Integer> map1 = new HashMap<>(map);
        return map1;
    }

    //todo:pb117
    public static Map<String, Integer> merge2Maps(Map<String, Integer> map1, Map<String, Integer> map2) {
        map1.putAll(map2);
        return map1;
    }

    //todo:pb118
    public static List<String> removeElement(List<String> lista) {
        lista.removeIf(t -> t.length() == 6);
        return lista;
    }

    //todo:pb119
    public static Object[] collectionToArray(List<String> lista) {
        return lista.toArray();
    }

    //todo:pb120
    public static List<Caine> filtruCaini(List<Caine> lista) {
        List<String> numeCaini = Arrays.asList("dsadsa", "3425");
        List<Caine> rezultate = lista.stream().filter(t -> numeCaini.contains(t.getNume())).collect(Collectors.toList());
        return rezultate;
    }

    //todo:pb121
    public static void replaceElement() {
        List<String> lista = new ArrayList<>(2);
        lista.add("asdsa");
        lista.add("5463tretew");
        lista.set(0, "catel");

    }

    //todo:pb123
    //clasa graph functia bfs

    //todo:pb124
    //clasa trie si nodetrie


    //todo:pb125
    //clasa tuple

    //todo:pb129
    public static void pathExamples() {
        Path p1 = Paths.get("com/company/Graph.java");
        Path p2 = Paths.get("src/com/company/Graph.java");
        Path p3 = Paths.get("D:\\mcs\\JavaBasics\\CarteJavaProblem\\DateAndTime2\\src\\com\\company\\Graph.java");

    }

    //todo:pb130

    public static void convertPath() throws MalformedURLException {
        Path p1 = Paths.get("com/company/Graph.java");

        URI uri = p1.toUri();
        URL url = uri.toURL();
        String str = p1.toString();
    }


    //todo:pb132
    public static void pathBetweenLocations() {
        Path p1 = Paths.get("com/company/Graph.java");
        Path p2 = Paths.get("com/company/Trie.java");
        Path p1Top2 = p1.relativize(p2);
    }

    //todo:pb133
    public static void compare2Paths() {
        Path p1 = Paths.get("com/company/Graph.java");
        Path p2 = Paths.get("com/company/Trie.java");
        System.out.println(p1.compareTo(p2));
        System.out.println(p1.equals(p2));

    }

    //todo:pb134

    //clasele DeleteFileVisitor, SearchFV, CopyFV

    //todo:pb135
    public void watchFolder(Path path) throws IOException, InterruptedException {
        WatchService watchService = FileSystems.getDefault().newWatchService();
        path.register(watchService, StandardWatchEventKinds.ENTRY_CREATE, StandardWatchEventKinds.ENTRY_MODIFY, StandardWatchEventKinds.ENTRY_DELETE);
        while (true) {
            WatchKey watchKey = watchService.take();
            for (WatchEvent watchEvent : watchKey.pollEvents()) {
                WatchEvent.Kind kind = watchEvent.kind();
                WatchEvent<Path> watchEventPath = (WatchEvent<Path>) watchEvent;
                Path filename = watchEventPath.context();
                boolean valid = watchKey.reset();
                if (!valid)
                    break;
            }


        }
    }


    //todo:pb136
    public static void streamingContent(Path path) {
        try (BufferedReader brStrean = Files.newBufferedReader(Paths.get(String.valueOf(path)), StandardCharsets.UTF_8)) {
            brStrean.lines().forEach(System.out::println);
        } catch (IOException e) {

        }
    }


    //todo:pb137

//    public static Stream<Path> fetchFIlesMAtching(Path root, String syntaxPAttern) throws IOException{
//        PathMatcher matcher=root.getFileSystem().getPathMatcher(syntaxPAttern);
//        return Files.find(root, MAX_VALUE,(path,attr)->matcher.matches(path)&amp;&amp;!attr.isDirectory());
//    }
//
//    public static Stream<Path> find(Path start, int maxDepth, BiPredicate<Path, BasicFileAttributes> matcher, FileVisitOption options) throws IOException{
//        Path startPath=Paths.get("C:\\Users\\Andrei Maracine\\Desktop\\mcs\\pentruGitHub");
//        Stream<Path> result=fetchFIlesMAtching(startPath,"sintaxa");
//        return result;
//    }


    //todo:pb138
    public static void read(Path file) throws IOException {
        try (FileChannel fileChannel = (FileChannel.open(file, EnumSet.of(StandardOpenOption.READ)))) {
            MappedByteBuffer mbBuffer = fileChannel.map(FileChannel.MapMode.READ_ONLY, 0, fileChannel.size());
            if (mbBuffer != null) {
                String bufferContent = StandardCharsets.UTF_16.decode(mbBuffer).toString();
                System.out.println(bufferContent);
                mbBuffer.clear();
            }

        }
    }


    public static void write() throws IOException {
        Path textFile = Paths.get("sample.txt");
        String line = "asdasd";
        Files.writeString(textFile, line, StandardCharsets.UTF_8, StandardOpenOption.CREATE, StandardOpenOption.WRITE);
    }

    //todo:pb139
    public static void readBinary(Path file) throws IOException {
        try (FileChannel fileChannel = (FileChannel.open(file, EnumSet.of(StandardOpenOption.READ)))) {
            MappedByteBuffer mbBuffer = fileChannel.map(FileChannel.MapMode.READ_ONLY, 0, fileChannel.size());
            System.out.println(mbBuffer.limit());
        }
    }

    public static void writeBinary() throws IOException {
        byte[] buffer = {0, 1};
        Path classFIle = Paths.get("C:\\Users\\Andrei Maracine\\Desktop\\mcs\\pentruGitHub");
        Files.write(classFIle, buffer, StandardOpenOption.CREATE, StandardOpenOption.WRITE);

    }

    //todo:pb140
    public static boolean isEmpty(String s) {
        return s == null || s.length() == 0;
    }


    public static int countMatches(String text, String str) {
        if (isEmpty(text) || isEmpty(str)) {
            return 0;
        }

        int index = 0, count = 0;
        while (true) {
            index = text.indexOf(str, index);
            if (index != -1) {
                count++;
                index += str.length();
            } else {
                break;
            }
        }

        return count;
    }

    public static int count(Path path, String text, Charset ch) throws IOException {
        return Files.readAllLines(path, ch).parallelStream().mapToInt((p) -> countMatches(p, text)).sum();
    }


    //todo:pb141

    //nu merge extensia de json

    //todo:pb142
    public static void operatii() throws IOException {
        Path tmp = Files.createTempDirectory(null, null);
        Path file1 = Files.createTempFile(tmp, null, null);
        Path file2 = Files.createTempFile(tmp, null, null);
        Path file3 = Files.createTempFile(tmp, null, null);

        try (DirectoryStream<Path> ds = Files.newDirectoryStream(tmp)) {
            tmp.toFile().deleteOnExit();

            for (Path file : ds) {
                file.toFile().deleteOnExit();
            }
        } catch (IOException e) {

        }
    }


    //todo:pb143
    public static void filter(Path path) {
        File[] folders = path.toFile().listFiles((File file) -> file.isDirectory());
    }

    //todo:pb144
    public static long mismatch2Files(Path p1, Path p2) throws IOException {
        long mismatch = Files.mismatch(p1, p2);
        return mismatch;
    }

    //todo:pb145
    //clasa circularByteBuffer


    //todo:pb146
    public static List<String> get(Path path, Charset cs, String delimiter) throws IOException {
        String delimiterStr = Pattern.quote(delimiter);
        List<String> content = new ArrayList<>();
        try (BufferedReader br = Files.newBufferedReader(path, cs)) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(delimiter);
                content.addAll(Arrays.asList(values));
            }
        }
        return content;
    }

    //todo:pb147
    public static void format1(Path path, int len, Integer[] arr1, Double[] arr2) throws FileNotFoundException {
        try (Formatter output = new Formatter(path.toFile())) {
            for (int i = 0; i < len; i++)
                output.format("| %6s | %3f | %n ", arr1[i], arr2[i]);
        }
    }

    //todo:pb148
    private static void scannerExample1(Path path) throws IOException {
        try (Scanner scan = new Scanner(path, StandardCharsets.UTF_8)) {
            while (scan.hasNext()) {
                String line = scan.next();
                System.out.println(line);
            }
        }
    }

    //todo:pb149
    public static void packagesExamples() {
        Package[] ps = Package.getPackages();
    }

    public static List<Class<?>> fetchClassesFromPackages(String packageName) throws URISyntaxException {
        String prefix = "jar";
        List<Class<?>> classes = new ArrayList<>();
        String packagePath = packageName.replace('.', '/');
        URL resource = ClassLoader.getSystemClassLoader().getResource(packagePath);
        if (resource != null) {
            if (resource.toString().startsWith(prefix)) {
                classes.addAll(fetchClassesFromPackages(packageName));
            }

        }

        return classes;
    }

    //todo:pb150
    public static void classInspection() {
        Caine caine1 = new Caine("azorica", 13);
        Class clasaCaine = caine1.getClass();
        String s1 = clasaCaine.getSimpleName();
        int modifiers = clasaCaine.getModifiers();
        System.out.println("is public?" + Modifier.isPublic(modifiers));
        System.out.println("is abstract?" + Modifier.isAbstract(modifiers));
        Class[] interfaces = clasaCaine.getInterfaces();//luam toate interfetele
        Constructor[] constructors = clasaCaine.getConstructors();
        Field[] fields = clasaCaine.getFields();
    }

    //todo:pb151
    public static void constructorReflexie() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Class<Caine> clasaCaine = Caine.class;
        Constructor<Caine> const1 = clasaCaine.getConstructor(String.class, String.class);
        Caine c1 = const1.newInstance("azorel", "albastru");

    }

    //todo:pb152
    public static void annotReceiv() throws NoSuchMethodException {
        Class clasaCaine = Caine.class;
        Method metodaAlearga = clasaCaine.getDeclaredMethod("alearga");
        AnnotatedType annotatedType = metodaAlearga.getAnnotatedReceiverType();
        System.out.println("tipul este:" + annotatedType.getType());
        System.out.println("anotatiile sunt" + Arrays.toString(annotatedType.getAnnotations()));
        System.out.println(annotatedType.getAnnotatedOwnerType());


    }

    //todo:pb153
    public static void syntheticBridge() {
        Class blanaClasa = Caine.Blana.class;
        Field[] fields = blanaClasa.getFields();//memoreaza field-ul this al clasei blana care e sintetic
    }

    //o metoda bridge sintetica este compare din clasa Caine

//todo:pb154

    public static void variableArgs() {
        Class caineClasa = Caine.class;
        Method[] methods = caineClasa.getMethods();
        System.out.println(methods[0].isVarArgs());

    }

//todo:pb155

    public static void defaulMethods() {
        Class caine = Caine.class;
        Method[] methods = caine.getMethods();
        System.out.println(methods[0].isDefault());
    }

    //todo:pb156
    public static void nestReflection() {
        Class caineClasa = Caine.class;
        Class blanaClasa = Caine.Blana.class;
        System.out.println(caineClasa.getNestHost());//clasa Caine, idem pt Blana
        System.out.println(blanaClasa.getNestMembers());//clasele caine si blana
        System.out.println(blanaClasa.isNestmateOf(caineClasa));//true
    }

    //todo:pb157
    public static void getGetterSetter() {
        Class caineClasa = Caine.class;
        Stream<Method> getters = Arrays.stream(caineClasa.getMethods()).filter((method -> method.toString().startsWith("get")));
        Stream<Method> setters = Arrays.stream(caineClasa.getMethods()).filter((method -> method.toString().startsWith("set")));
    }

//todo:pb158

    public static void differentAnnotations() throws NoSuchMethodException, NoSuchFieldException {
        Class caineClasa = Caine.class;
        Annotation[] annotations = caineClasa.getPackage().getAnnotations(); //anotatiile pachetului
        Annotation[] annotations1 = caineClasa.getAnnotations();//anotatiile clasei
        Method methodAlearga = caineClasa.getDeclaredMethod("alearga");
        Annotation[] annotations2 = methodAlearga.getAnnotations(); //anotatiile metodei
        Field age = caineClasa.getField("varsta");
        Annotation[] annotations3 = age.getAnnotations();//anoattiile field-ului
    }

    //todo:pb159
    public static void invokeInstance() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Class caineClasa = Caine.class;
        Method metodaMananca = caineClasa.getDeclaredMethod("mananca", String.class, int.class);
        Caine instantaCaine = Caine.class.getDeclaredConstructor().newInstance();
        List<Caine> cainiMancaciosi = (List<Caine>) metodaMananca.invoke(instantaCaine, "pizza", 7, 9);
    }

    //todo:pb160

    public static void staticMethods() {
        Class clasaCaine = Caine.class;
        Method[] methods = clasaCaine.getDeclaredMethods();

        List<Method> statics = new ArrayList<>();

        for (int i = 0; i < methods.length; i++)
            if (Modifier.isStatic(methods[i].getModifiers()))
                statics.add(methods[i]);
    }

    //todo:pb161

    public static void generics1() throws NoSuchMethodException, NoSuchFieldException {
        Class caineClasa = Caine.class;
        Method aleargaMetoda = caineClasa.getDeclaredMethod("alearga");
        Type aleargaType = aleargaMetoda.getGenericReturnType();//generics pentru metode
        Field ageField = caineClasa.getField("varsta");
        Type ageType = ageField.getGenericType(); //generics pentru fielduri
        Class blanaClasa = Caine.Blana.class;
        Type blanaType = blanaClasa.getGenericSuperclass();//generics pentru superclasa

    }

    //todo:pb162

    public static void publicPrivat() {
        Class caineClasa = Caine.class;
        Field[] fields = caineClasa.getFields();
        List<Field> publics = new ArrayList<>();
        List<Field> privates = new ArrayList<>();
        for (int i = 0; i < fields.length; i++)
            if (Modifier.isPublic(fields[i].getModifiers()))
                publics.add(fields[i]);

        for (int i = 0; i < fields.length; i++)
            if (Modifier.isPrivate(fields[i].getModifiers()))
                privates.add(fields[i]);
    }

    //todo:pb163
    public static void workArrays() {
        int[] arrayInt = (int[]) Array.newInstance(int.class, 10);
        Array.setInt(arrayInt, 0, 7);
        int value1 = Array.getInt(arrayInt, 1);
        Class stringClasa = String.class;
        Type stringType = stringClasa.getComponentType();
    }

    //todo:pb164
    public static void moduleWork() {
        Module caineModule = Caine.class.getModule();
        System.out.println(caineModule.getName());//numele modului
        boolean caineModuleExported = caineModule.isExported("com.company");
        caineModule.addExports("com.company", caineModule);
    }

    //todo:pb165
    //clasa CountingInvocationHandler

    //todo:pb166
    public static List<Caine> filterByName(List<Caine> caini, String name) {
        List<Caine> rezultate = new ArrayList<>();
        for (Caine caine : caini)
            if (caine.getNume().equalsIgnoreCase(name))
                rezultate.add(caine);
        return rezultate;
    }


    public static List<Caine> filterByNameAndAge(List<Caine> caini, String nume, int age) {
        List<Caine> rezultate = new ArrayList<>();
        for (Caine caine : caini) {
            if (caine != null && nume.equalsIgnoreCase(caine.getNume()) && caine.getVarsta() == age)
                rezultate.add(caine);
        }

        return rezultate;

    }

    public static List<Caine> filtrareCaini(List<Caine> lista, ICaine caine2) {
        List<Caine> rezultate = new ArrayList<>();
        for (Caine caine : lista) {
            if (caine != null && caine2.latra()) {
                rezultate.add(caine);
            }
        }
        return rezultate;
    }


    public List<Caine> anotherFilter(List<Caine> list) {
        List<Caine> dogs = filtrareCaini(list, new ICaine() {
            @Override
            public boolean latra() {
                return false;
            }
        });

        return dogs;
    }

    //todo:pb167
    //functia lambda este o forma mai usoara de a scrie o functie
    //in care dam doar parametrii si corpul functiei

    //todo:pb168
    public static double read(ScannerDoubleFunction snf) throws IOException {
        try (Scanner scanner = new Scanner(Path.of("fisier.txt"), StandardCharsets.UTF_8)) {
            return snf.readDouble(scanner);
        }
    }


    public static double getFirst(Scanner scanner) {
        if (scanner.hasNextDouble())
            return scanner.nextDouble();

        return Double.NaN;
    }

    public static double first() throws IOException {
        double first = read((Scanner sc) -> getFirst(sc));
        return first;
    } //Execute Arround Pattern este ok pentru a scapa de codul repetitiv inutil

    //todo:pb169
    public static ICaine newInstance(Class clas) {
        switch (clas.getSimpleName()) {
            case "Husky":
                return new Husky();
            case "Beagle":
                return new Beagle();
            default:
                throw new IllegalArgumentException(

                );
        }

    }

    public static void examplesFactory() {
        Supplier<ICaine> husky = Husky::new;
    }
    //Factory Pattern se refera la cum sa cream mai usor obiecte apartinand
    //aceleiasi interfete

    //todo:pb170
    public static void strategyExample() {
        String text = "sadadsa";
        String noNr = Remover.remove(text, new NumberRemover());
        String noNr2 = Remover.remove(text, (s) -> s.replaceAll("\\d", ""));
    }

    //todo:pb171
    public static void templateExample() {
        Pizza pizza = new Pizza();
        new PizzaLambda().make(pizza, (Pizza p) -> System.out.println("adauga rosii masline alea alea"));
    }

    //todo:pb172
    public static void observerExample() {
        FireStation fireStation = new FireStation();
        fireStation.registerFireStation(new StatieIasi());
        fireStation.notifyFireStation("la stanga");
        fireStation.registerFireStation((String adr) -> {
            if (adr.contains("iasi"))
                System.out.println("iasi va arde");
        });
        //este un pattern tot pentru a evita aparitia codului inutil
    }

    //todo:pb173
    public static void LoanExample() throws IOException {
        double xPlusYMinusZ = Formula.compute((sc) -> sc.add().add().minus().getResult());
    }


    //todo:pb174
    public static void decoratorExample(){
        Cake cake=new Nuts(new BaseCake());
    }

    //todo:pb175

    public static void cascadedExample(){
        Delivery.deliver(d->d.firstname("Andrei").lastName("Daniel"));
    }

    //todo:pb176
    public static  void CommandExample(){
        HardDisk hd=new HardDisk();
        Sequence seq=new Sequence();
        seq.recordSequence(new DeleteCommand(hd));
        seq.recordSequence(new DeleteCommand(hd));
        seq.recordSequence(hd::delete);
        seq.runSequence();
    }

    //todo:pb177

    public static List<String> replace(List<String> list, Replacer<String> r){
        List<String> result=new ArrayList<>();
        for(String s:list){
            result.add(r.replace(s));
        }
        return result;
    }

    public static Function<String, String> reduceStrings(Function<String, String> f1, Function<String, String> functions){
        Function<String,String> function= Stream.of(functions).reduce(Function.identity(), Function::andThen);
        return function;
    }

    public static final Function<String,String> firstLastChar=(String s)->String.valueOf(s.charAt(0))+String.valueOf(s.charAt(s.length()-1));
    public List<String>  rndStrStr(List<String> strs){
        return  strs.stream().map(Problems::extractRandChar).collect(Collectors.toList());

    }

    public static String extractRandChar(String str){
        Random rnd=new Random();
        int nr=rnd.nextInt(str.length());
        String ch=String.valueOf(str.charAt(nr));
        return ch;
    }

    //todo:pb179
    public static void debugLambdas(){
        List<String> names=List.of("anca","ion","George");
        names.stream().peek(p-> System.out.println("caz1" + p)).filter((s)->s.startsWith("a")).peek(p-> System.out.println("Caz 2"+p)).map(String::toUpperCase).peek(p-> System.out.println("caz 3" + p)).collect(Collectors.toList());

    }

    //todo:pb180
    public static List<Integer> filterNon0(List<Integer> ints){
        List<Integer> results= ints.stream().filter(i->i!=0).collect(Collectors.toList());
        return results;
    }

    //todo:pb181
        public static void unlimitedStreamsExamples(){
        Stream infStream=Stream.iterate(1,i->i<=10, i-> i+i%2==0 ? new Random().nextInt(20):-1*new Random().nextInt(10));
        List<Integer> result= (List<Integer>) infStream.limit(25).collect(Collectors.toList());
            IntStream rndIntStream=new Random().ints(10,1,100);
            List<Integer> result2=rndIntStream.filter(i->i%2==0).boxed().collect(Collectors.toList());
            List<Integer> result3=new Random().ints(1,100).takeWhile(i->i%2==0).takeWhile(i->i>=50).boxed().collect(Collectors.toList());
            List<Integer> result4=new Random().ints(1,100).takeWhile(i->i%2==0 && i>=50).boxed().collect(Collectors.toList());
        }

        //todo:pb182
    public static List<String> mapElementsClass(){
        List<Caine> lista=Arrays.asList(new Caine("sads",13),new Caine("bbb",4),new Caine("vbnm",4567));
        List<String> numeCaini=lista.stream().map(Caine::getNume).collect(Collectors.toList());
        return numeCaini;
    }

    //todo:pb183
        public static void findElementStream(){
            List<Caine> lista=Arrays.asList(new Caine("sads",13),new Caine("bbb",4),new Caine("vbnm",4567));
            List<String> numeCaini=lista.stream().map(Caine::getNume).collect(Collectors.toList());
            Optional<String> randomCaine = numeCaini.stream().findAny();
            if(randomCaine.isEmpty()==false)
                System.out.println(randomCaine.get());
            else
                System.out.println("nu avem niciun caine");//aici folosesc findAny
            Optional<String> primNume= numeCaini.stream().findFirst();
            if(primNume.isEmpty()==false)
                System.out.println(primNume.get());

        }

        //todo:pb184
    public static void matchingElements(){
        List<Caine> lista=Arrays.asList(new Caine("sads",13),new Caine("bbb",4),new Caine("vbnm",4567));
        List<String> numeCaini=lista.stream().map(Caine::getNume).collect(Collectors.toList());
        boolean isAnyBob=numeCaini.stream().anyMatch((s)->s.equalsIgnoreCase("bob"));
    }

    //todo:pb185
        public static void sumReduceStream(){
            List<Caine> lista=Arrays.asList(new Caine("sads",13),new Caine("bbb",4),new Caine("vbnm",4567));
            List<Integer> numeCaini=lista.stream().map(Caine::getVarsta).collect(Collectors.toList());
            int sum=lista.stream().mapToInt(Caine::getVarsta).sum();
            int max=lista.stream().mapToInt(Caine::getVarsta).max().orElse(-7);
            int min=lista.stream().mapToInt(Caine::getVarsta).min().orElse(-7);
            int reduceMax=lista.stream().map(Caine::getVarsta).reduce(Integer::max).orElse(-7);
        }

        //todo:pb186
    public static void collectStream(){
        List<Caine> lista=Arrays.asList(new Caine("sads",13),new Caine("bbb",4),new Caine("vbnm",4567));
        List<String> numeCaini=lista.stream().map(Caine::getNume).collect(Collectors.toList());
        List<String> nume7=lista.stream().map(Caine::getNume).filter(s->s.length()==7).collect(Collectors.toCollection(ArrayList::new));
        //asemanator ne putem folosi de .toSet() sau .toCollection

    }

    //todo:pb187
    public static void joinResultStream(){
        List<Caine> lista=Arrays.asList(new Caine("sads",13),new Caine("bbb",4),new Caine("vbnm",4567));
        String numeCaini=lista.stream().map(Caine::getNume).filter(s->s.equalsIgnoreCase("rata")).collect(Collectors.joining(",","hei","la revedere"));

    }

    //todo:pb188
    public static void summarizationCollectors(){
        List<Caine> lista=Arrays.asList(new Caine("sads",13),new Caine("bbb",4),new Caine("vbnm",4567));
        List<String> numeCaini=lista.stream().map(Caine::getNume).collect(Collectors.toList());
        List<Integer> varsteCaini=lista.stream().map(Caine::getVarsta).collect(Collectors.toList());
        int sum= lista.stream().collect(Collectors.summingInt(Caine::getVarsta));
        //similar cu summingDouble si summingFloat
        double reduceAge=lista.stream().collect(Collectors.reducing(0,(Caine c)->c.getVarsta(),(m1,m2)->m1+m2));
        double avgVarsta=lista.stream().collect(Collectors.averagingInt(Caine::getVarsta));
        double countVarsta=lista.stream().collect(Collectors.counting());

    }

    //todo:pb189
    public static void grouping(){
        List<Caine> lista=Arrays.asList(new Caine("sads",13),new Caine("bbb",4),new Caine("vbnm",4567));
        List<String> numeCaini=lista.stream().map(Caine::getNume).collect(Collectors.toList());
        List<Integer> varsteCaini=lista.stream().map(Caine::getVarsta).collect(Collectors.toList());
        Map<String,List<Caine>> byName=lista.stream().collect(groupingBy(Caine::getNume));
        Map<Integer,List<Caine>> byAge=lista.stream().collect(groupingBy(Caine::getVarsta));

    }







    //todo:pb190
    public static void partitioning(){
        List<Caine> lista=Arrays.asList(new Caine("sads",13),new Caine("bbb",4),new Caine("vbnm",4567));
        List<String> numeCaini=lista.stream().map(Caine::getNume).collect(Collectors.toList());
        List<Integer> varsteCaini=lista.stream().map(Caine::getVarsta).collect(Collectors.toList());
        Map<Boolean,List<Caine>> byAge=lista.stream().distinct().collect(Collectors.partitioningBy((Caine c)->c.getVarsta()<77));
        Map<Integer,Set<Caine>> byAgeOrderd=lista.stream().collect(groupingBy(Caine::getVarsta,TreeMap::new,toSet())); //acum sunt sortate intr un set

    }











}













































