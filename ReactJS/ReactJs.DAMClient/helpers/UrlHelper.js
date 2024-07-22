class UrlHelper {
  static slugify(url) {
    if (url) {
      return url.toLowerCase().replace(/[^\w ]+/g, '').replace(/ +/g, '-');
    }

    return '';
  }
  static isValidUrl(urlString){
    try { 
      return Boolean(new URL(urlString)); 
    }
    catch(e){ 
      return false; 
    }
  }
  static getFileName(url){
    if(url){
      const fileName = url.replace(/^.*[\\\/]/, '');

      if (fileName.includes('.')) {
        const fileNameSplit = fileName.split('.');
        return  fileNameSplit[0];
      }
      return fileName;
    }
    return 'File-Download';
  }
}

export default UrlHelper;