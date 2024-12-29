"use client";
import { AuthFormProps, AuthProps } from "@/app/types/interfaces";
import { validationRules } from "@/app/types/validationRules";
import Link from "next/link";
import { SubmitHandler, useForm } from "react-hook-form";

type FormValues = {
  [key: string]: string;
};

export default function AuthForm({
  welcome,
  greeting,
  actionText,
  actionLink,
  formFields,
  buttonText,
}: AuthProps) {
  const {
    register,
    handleSubmit,
    formState: { errors },
  } = useForm<FormValues>({
    mode: "onBlur",
  });

  async function handleLogin(data: Record<string, string>) {
    const formData = new FormData();
    formData.append("username", data["email"]);
    formData.append("password", data["password"]);

    const res = await fetch(`http://localhost:8080/login`, {
      method: "post",
      credentials: "include",
      body: formData,
    });

    if (res.ok) {
      document.location = "/home";
    } else {
      window.alert("zle passy");
    }
  }

  async function handleRegister(data: Record<string, string>) {
    console.log(data);
    const regData = {
      firstname: data["name"].split(" ")[0],
      lastname: data["name"].split(" ")[1],
      email: data["email"],
      password: data["password"],
    };

    const res = await fetch(`http://localhost:8080/register`, {
      method: "post",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(regData),
    });

    if (res.ok) {
      document.location = "/home";
    } else {
      window.alert("something is no yes");
    }
  }

  const onSubmit: SubmitHandler<Record<string, string>> = async (data) => {
    console.log(actionLink);

    if (actionLink == "/auth/login") await handleLogin(data);
    if (actionLink == "/auth/register") await handleRegister(data);
  };

  return (
    <div className="text-neutral-100 mx-auto flex flex-col justify-center items-center w-full p-10 md:w-3/5 max-w-3xl h-full mt-auto ">
      <div className="flex flex-col justify-center items-center text-center ">
        <h1 className="font-bold font-oswald text-3xl md:text-3xl lg:text-5xl">
          {welcome}
        </h1>
        <h3 className=" text-neutral-200 text-xl md:text-xl lg:text-2xl my-8">
          {greeting}
        </h3>
      </div>
      <div className="flex flex-col justify-center items-center w-full max-w-md">
        <form
          className="w-full flex flex-col gap-y-3"
          autoComplete="off"
          noValidate={true}
          onSubmit={handleSubmit(onSubmit)}
        >
          {formFields.map((field: AuthFormProps) => (
            <div key={field.id} className="flex flex-col">
              <input
                className={`w-full p-4 bg-zinc-800 box-border border-b-2 transition duration-150 ease-in-out autofill:none focus:outline-none focus:ring-0 ${
                  errors[field.id]
                    ? "border-red-500"
                    : "border-zinc-800 focus:border-orange-500"
                }`}
                id={field.id}
                type={field.type}
                placeholder={field.placeholder}
                {...register(
                  field.id,
                  validationRules[field.id as keyof typeof validationRules]
                )}
              />
              {errors[field.id] && (
                <p className="text-red-500 text-sm mt-1">
                  {errors[field.id]?.message as string}
                </p>
              )}
            </div>
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
