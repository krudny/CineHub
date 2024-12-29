"use client";

async function logOut() {
  const res = await fetch(`http://localhost:8080/logout`, {
    credentials: "include",
  });
  if (res.ok) {
    document.location = "/home";
  } else {
    window.alert("error");
  }
}

export default function Page() {
  logOut();
  return (
    <div className="text-white text-2xl text-center w-full p-8">
      logout in progress, please wait
    </div>
  );
}
