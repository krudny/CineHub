import Link from "next/link";
import { AuthFormProps, AuthProps } from "@/app/types/interfaces";

export default function Auth({
  welcome,
  greeting,
  actionText,
  actionLink,
  formFields,
  buttonText,
}: AuthProps) {
  return (
    <div className="text-neutral-100 mx-auto flex flex-col justify-center items-center w-full p-10 md:w-3/5 max-w-3xl h-full mt-auto ">
      <div className="flex flex-col justify-center items-center text-center ">
        <h1 className="font-bold font-oswald text-3xl md:text-4xl lg:text-5xl">
          {welcome}
        </h1>
        <h3 className=" text-neutral-200 text-xl md:text-2xl my-10">
          {greeting}
        </h3>
      </div>
      <div className="flex flex-col justify-center items-center w-full max-w-md">
        <form className="w-full flex flex-col gap-y-3" autoComplete="off">
          {formFields.map((field: AuthFormProps) => (
            <input
              className="w-full p-4 bg-zinc-800 box-border border-b-2 border-zinc-800 focus:border-orange-500 transition duration-150 ease-in-out autofill:none focus:outline-none  focus:ring-0"
              key={field.id}
              id={field.id}
              type={field.type}
              placeholder={field.placeholder}
            />
          ))}
          <button
            className="w-full p-4 bg-orange-500 border-none transition duration-150 ease-in-out hover:bg-orange-600"
            type="submit"
          >
            {buttonText}
          </button>
        </form>
        <Link href={actionLink}>
          <p className="text-neutral-100 mt-6 text-md hover:cursor-pointer">
            {actionText}
          </p>
        </Link>
      </div>
    </div>
  );
}
