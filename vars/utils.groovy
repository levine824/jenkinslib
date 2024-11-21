import java.text.SimpleDateFormat

// 获取带时间戳的包名
def getPackageName(String packageName) {
    def sdf = new SimpleDateFormat("yyyyMMdHHmmss")
    def dateStr = sdf.format(Calendar.getInstance().getTime())

    def index = packageName.indexOf(".")
    def name = packageName.substring(0, index)
    def suffix = packageName.substring(index)

    if (suffix == ".tar.gz" || suffix == ".apk" || suffix == ".ipa") {
        return name + "-" + dateStr + suffix
    } else {
        echo "文件名不符合规范: ${packageName}"
        return packageName
    }

}

