import store from "../store/index.js";
const cloudName = "dgupilxum";
const uploadPreset = "l1c4hxcn";

const myWidget = window.cloudinary.createUploadWidget(
    {
        cloudName: cloudName,
        uploadPreset: uploadPreset,
    },
    (error, result) => {
        if (!error && result && result.event === "success") {
            let profileUpload = result.info.secure_url;
            store.commit("SET_PROFILE_UPLOAD", profileUpload);
        }
    }
);


export default {
    myWidget
}