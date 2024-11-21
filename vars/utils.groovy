import java.text.SimpleDateFormat

// 获取带时间戳的包名
def getPackageName(String packageName) {
    def sdf = new SimpleDateFormat("yyyyMMdHHmmss")
    def dateStr = sdf.format(Calendar.getInstance().getTime())

    def packageNameList = Arrays.asList(packageName.split("\\.tar.gz|\\.apk|\\.ipa"))

    def newPackageName = packageName

    if (packageNameList.size() == 2) {
        newPackageName = packageNameList[0] + "-" + dateStr + packageNameList[1]
    }

    return newPackageName
}

