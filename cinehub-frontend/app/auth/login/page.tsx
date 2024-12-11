import Link from "next/link";

export default function Page() {
  return (
    <div className="text-neutral-100  mx-auto flex flex-col justify-center items-center w-full p-10 md:w-3/5 max-w-3xl h-full">
      <div className="flex flex-col justify-center items-center text-center ">
        <h1 className="font-bold font-oswald text-3xl md:text-4xl lg:text-6xl md:-mt-16">
          Login to Your Account
        </h1>
        <h3 className=" text-neutral-200 text-xl md:text-2xl lg:text-3xl my-14">
          Experience the magic of cinema with the latest blockbusters and
          timeless classics. Join us for unforgettable moments on the big
          screen!
        </h3>
      </div>
      <div className="flex flex-col justify-center items-center w-full max-w-md ">
        <form className=" w-full flex flex-col gap-y-4" autoComplete="off">
          <input
            className="w-full p-4 bg-zinc-800 box-border border-b-2 border-zinc-800 focus:border-orange-500 transition duration-150 ease-in-out autofill:none focus:outline-none  focus:ring-0"
            id="email"
            type="email"
            placeholder="Email"
          />
          <input
            className="w-full p-4 bg-zinc-800 box-border border-b-2 border-zinc-800 focus:border-orange-500 transition duration-150 ease-in-out autofill:none focus:outline-none  focus:ring-0"
            id="password"
            type="password"
            placeholder="Password"
          />
          <button
            className="w-full p-4 bg-orange-500 border-none transition duration-150 ease-in-out hover:bg-orange-600"
            type="submit"
          >
            Login
          </button>
        </form>
        <Link href="/auth/register">
          <p className="text-neutral-100 mt-10 text-md hover:cursor-pointer">
            Don't have account?
          </p>
        </Link>
        <p className="text-neutral-100 mt-4 text-md hover:cursor-pointer">
          Forgot password?
        </p>
      </div>
    </div>
  );
}
